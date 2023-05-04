package dev.irving.basecode.data.user

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.irving.basecode.domain.users.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class UserFirebaseSource(
    private val dispatcher: CoroutineDispatcher
) : UserRemoteSource {

    private val _userFlow = MutableStateFlow(User())
    override val userFlow: StateFlow<User> get() = _userFlow

    override fun fetchUser(userId: String) {
        val usersRef = Firebase.database.getReference("users")
        usersRef.child(userId).get().addOnSuccessListener {
            val user = it.getValue(UserResponse::class.java)
            Timber.d("Irving User $user")
            _userFlow.update { user?.toUser(userId) ?: User() }
        }.addOnFailureListener { exception ->
            Timber.e(exception)
        }
    }
}
