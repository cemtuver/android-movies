package tuver.movies.data.source

import tuver.movies.data.error.DtoNotFetchedError
import tuver.movies.data.source.dto.MovieDto
import tuver.movies.data.source.local.MovieDao
import tuver.movies.data.source.local.MovieDatabase
import tuver.movies.data.source.mapper.MovieMapper
import tuver.movies.data.source.remote.MovieApi
import tuver.movies.model.MovieModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.random.Random

class CachedMovieRepositoryTest {

    private val mockedMovieApi: MovieApi = mockk()

    private val mockedMovieDatabase: MovieDatabase = mockk()

    private val mockedMovieDao: MovieDao = mockk()

    private val mockedMovieMapper: MovieMapper = mockk()

    private val cachedMovieRepository = CachedMovieRepository(
        mockedMovieApi,
        mockedMovieDatabase,
        mockedMovieMapper
    )

    @Before
    fun setup() {
        every { mockedMovieDatabase.movieDao() } returns mockedMovieDao
    }

    @Test
    fun `should get movie from api, insert it into dao and return mapped model while getting movie`() {
        // given
        val movieId = Random.nextInt()
        val movieDto: MovieDto = mockk()
        val movieModel: MovieModel = mockk()
        val expectedResult = Result.success(movieModel)
        coEvery { mockedMovieApi.getMovie(eq(movieId)) } returns movieDto
        coEvery { mockedMovieDao.insert(eq(movieDto)) } returns Unit
        every { mockedMovieMapper.toModel(eq(movieDto)) } returns movieModel

        // when
        val result = runBlocking { cachedMovieRepository.getMovie(movieId) }

        // then
        coVerify { mockedMovieApi.getMovie(eq(movieId)) }
        confirmVerified(mockedMovieApi)
        coVerify { mockedMovieDao.insert(eq(movieDto)) }
        confirmVerified(mockedMovieDao)
        verify { mockedMovieMapper.toModel(eq(movieDto)) }
        confirmVerified(mockedMovieMapper)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should get movie from dao and return mapped model when api returns error while getting movie`() {
        // given
        val movieId = Random.nextInt()
        val movieDto: MovieDto = mockk()
        val movieModel: MovieModel = mockk()
        val expectedResult = Result.success(movieModel)
        coEvery { mockedMovieApi.getMovie(eq(movieId)) } throws IOException()
        coEvery { mockedMovieDao.select(eq(movieId)) } returns movieDto
        every { mockedMovieMapper.toModel(eq(movieDto)) } returns movieModel

        // when
        val result = runBlocking { cachedMovieRepository.getMovie(movieId) }

        // then
        coVerify { mockedMovieApi.getMovie(eq(movieId)) }
        confirmVerified(mockedMovieApi)
        coVerify { mockedMovieDao.select(eq(movieId)) }
        confirmVerified(mockedMovieDao)
        verify { mockedMovieMapper.toModel(eq(movieDto)) }
        confirmVerified(mockedMovieMapper)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should return error when api and dao return error while getting movie`() {
        // given
        val movieId = Random.nextInt()
        coEvery { mockedMovieApi.getMovie(eq(movieId)) } throws IOException()
        coEvery { mockedMovieDao.select(eq(movieId)) } returns null

        // when
        val result = runBlocking { cachedMovieRepository.getMovie(movieId) }

        // then
        coVerify { mockedMovieApi.getMovie(eq(movieId)) }
        confirmVerified(mockedMovieApi)
        coVerify { mockedMovieDao.select(eq(movieId)) }
        confirmVerified(mockedMovieDao)
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DtoNotFetchedError)
    }

    @Test
    fun `should not emit exceptions other than api errors while getting movie`() {
        // given
        val movieId = Random.nextInt()
        val exception = Exception("Not IO exception")
        coEvery { mockedMovieApi.getMovie(eq(movieId)) } throws exception

        // when
        assertThrows(exception.javaClass) {
            runBlocking { cachedMovieRepository.getMovie(movieId) }
        }

        // then
        coVerify { mockedMovieApi.getMovie(eq(movieId)) }
        confirmVerified(mockedMovieApi)
    }

}