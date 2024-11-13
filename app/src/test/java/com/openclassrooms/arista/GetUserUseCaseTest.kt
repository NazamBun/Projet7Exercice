package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * S'assurer que le use case GetUserUseCase récupère correctement
 * les informations de l'utilisateur depuis le repository.
 * Le test vérifie que le cas d'utilisation appelle la méthode getUser du repository
 * et retourne les données attendues
 */
@RunWith(JUnit4::class)
class GetUserUseCaseTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getUserUseCase = GetUserUseCase(userRepository)
    }

    @After
    fun `when repository returns a user, use case should return the same user`() = runBlocking {
        // Arrange
        val fakeUser = User(id = 1, name = "John Doe", email = "johndoe@example.com", password = "password123")
        Mockito.`when`(userRepository.getUser()).thenReturn(fakeUser)

        // Act
        val result = getUserUseCase.execute()

        // Assert
        assertEquals(fakeUser, result)
    }
}