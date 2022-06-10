package com.assignment.acronym.ui.fragment.landingfragment

import android.app.Application
import com.assignment.data.model.networkmodel.AcronymResponse
import com.assignment.data.model.networkmodel.ShortFormMeaning
import com.assignment.network.remote.Repository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LandingViewModelTest {

    @Mock lateinit var repository: Repository
    @Mock lateinit var application: Application

    private lateinit var viewModel: LandingViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = LandingViewModel(repository, application)
    }

    @Test
    fun `test function transformResponseToList should return list of strings`() {
        val actualResponse = listOf(
            AcronymResponse(
                sf = "test",
                listOf(
                    ShortFormMeaning(
                        lf = "testfullform"
                    )
                )
            )
        )
        val response = viewModel.transformResponseToList(actualResponse)

        Assert.assertEquals("testfullform", response.first())
    }
}