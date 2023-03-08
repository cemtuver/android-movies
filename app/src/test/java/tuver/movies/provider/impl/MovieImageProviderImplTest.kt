package tuver.movies.provider.impl

import android.widget.ImageView
import tuver.movies.util.extension.loadFromUrl
import io.mockk.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class MovieImageProviderImplTest {

    private val imageBaseUrl = "https://img.base.url/"

    private val posterSize = "psize100"

    private val backdropSize = "bsize300"

    private val posterPlaceholder = Random.nextInt()

    private val backdropPlaceholder = Random.nextInt()

    private val movieImageProviderImpl = MovieImageProviderImpl(
        imageBaseUrl,
        posterSize,
        backdropSize,
        posterPlaceholder,
        backdropPlaceholder
    )

    @Before
    fun setup() {
        mockkStatic("tuver.movies.util.extension.ImageViewExtensionKt")
    }

    @Test
    fun `should construct poster url and load`() {
        // given
        val posterUrlPath = "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png"
        val imageView: ImageView = mockk()
        val expectedUrl = "$imageBaseUrl$posterSize$posterUrlPath"
        every { imageView.loadFromUrl(eq(expectedUrl), eq(posterPlaceholder)) } returns Unit

        // when
        movieImageProviderImpl.loadPoster(imageView, posterUrlPath)

        // then
        verify { imageView.loadFromUrl(eq(expectedUrl), eq(posterPlaceholder)) }
        confirmVerified(imageView)
    }

    @Test
    fun `should not construct poster url but load null when posterUrlPath is null`() {
        // given
        val posterUrlPath: String? = null
        val imageView: ImageView = mockk()
        every { imageView.loadFromUrl(isNull(), eq(posterPlaceholder)) } returns Unit

        // when
        movieImageProviderImpl.loadPoster(imageView, posterUrlPath)

        // then
        verify { imageView.loadFromUrl(isNull(), eq(posterPlaceholder)) }
        confirmVerified(imageView)
    }

    @Test
    fun `should construct backdrop url and load`() {
        // given
        val backdropUrlPath = "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png"
        val imageView: ImageView = mockk()
        val expectedUrl = "$imageBaseUrl$backdropSize$backdropUrlPath"
        every { imageView.loadFromUrl(eq(expectedUrl), eq(backdropPlaceholder)) } returns Unit

        // when
        movieImageProviderImpl.loadBackdrop(imageView, backdropUrlPath)

        // then
        verify { imageView.loadFromUrl(eq(expectedUrl), eq(backdropPlaceholder)) }
        confirmVerified(imageView)
    }

    @Test
    fun `should not construct backdrop url but load null when backdropUrlPath is null`() {
        // given
        val backdropUrlPath: String? = null
        val imageView: ImageView = mockk()
        every { imageView.loadFromUrl(isNull(), eq(backdropPlaceholder)) } returns Unit

        // when
        movieImageProviderImpl.loadBackdrop(imageView, backdropUrlPath)

        // then
        verify { imageView.loadFromUrl(isNull(), eq(backdropPlaceholder)) }
        confirmVerified(imageView)
    }

}