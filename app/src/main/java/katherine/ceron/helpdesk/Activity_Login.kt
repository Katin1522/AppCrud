package katherine.ceron.helpdesk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Llama a la función para activar el modo borde a borde
        setContentView(R.layout.activity_login)

        val txtUsuario = findViewById<EditText>(R.id.txtUsuarioL)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaL)
        val btnLogin = findViewById<Button>(R.id.btnIngresar)

        btnLogin.setOnClickListener {
            val usuario = txtUsuario.text.toString().trim()
            val contrasena = txtContrasena.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Ingrese los datos correctamente", Toast.LENGTH_SHORT).show()
            } else {
                // Simulación de autenticación exitosa
                if (autenticarUsuario(usuario, contrasena)) {
                    Toast.makeText(this, "Bienvenido a HelpDesk", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun autenticarUsuario(usuario: String, contrasena: String): Boolean {
        return true
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun enableEdgeToEdge() {


    }    }