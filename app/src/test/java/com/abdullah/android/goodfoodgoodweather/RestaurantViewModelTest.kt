package com.abdullah.android.goodfoodgoodweather

import android.os.Build.VERSION_CODES.P
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config


inline fun <reified T : Any?> mock(java: Class<List<YelpRestaurant>>) = Mockito.mock(T::class.java)
@RunWith(AndroidJUnit4::class)
@Config(sdk = [P], application =GoodFoodGoodWeatherApplication::class )
class RestaurantViewModelTest {
    //Field restaurantRepository of type RestaurantRepository - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set

    @Mock
    var mBagOfTags: Map<String, Any>? = null

    @Mock
    lateinit var restaurantRepository: RestaurantRepository

    @InjectMocks
    lateinit var restaurantViewModel: RestaurantViewModel

    @Mock
    lateinit var yelpRestaurants: List<YelpRestaurant>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val context = InstrumentationRegistry.getInstrumentation().context

        RestaurantRepository.initialize(context)
        restaurantRepository = RestaurantRepository.get()

        restaurantViewModel = RestaurantViewModel()

    }
    @Test
    fun testRestaurantsLiveData() {
        val result = restaurantViewModel.restaurantsLiveData().observeForever{
            Assert.assertNotNull(it)
        }
    }

    @Test
    fun testDeleteRestaurant() {
        restaurantViewModel.deleteRestaurant()
        restaurantViewModel.restaurantsLiveData().observeForever {
            Assert.assertNull(it)
        }
    }





}
