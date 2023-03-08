package tuver.movies.data.source.mapper.impl

import tuver.movies.data.source.dto.MovieDto
import tuver.movies.model.MovieModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class MovieMapperImplTest {

    private val mockReleaseDateFormat: SimpleDateFormat = mockk()

    private val movieMapperImpl = MovieMapperImpl(mockReleaseDateFormat)

    @Test
    fun `should map dto to model`() {
        // given
        val dto = MovieDto(
            505642,
            "Black Panther: Wakanda Forever",
            "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje...",
            "2019-01-01",
            "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
            "/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg",
            168
        )
        val expectedModel = dto.run {
            MovieModel(
                id,
                title,
                overview,
                Date(),
                posterImageUrlPath,
                backdropImageUrlPath,
                runtimeInMinutes
            )
        }
        every { mockReleaseDateFormat.parse(eq(dto.releaseDate)) } returns expectedModel.releaseDate

        // when
        val model = movieMapperImpl.toModel(dto)

        // then
        assertEquals(expectedModel, model)
    }

}