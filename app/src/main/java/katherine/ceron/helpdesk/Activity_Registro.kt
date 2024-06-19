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


class Activity_Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val txtUsuario = findViewById<EditText>(R.id.txtUsuario)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasena)
        val btnAgregarR = findViewById<Button>(R.id.btnAgregarR)

        btnAgregarR.setOnClickListener {
            val usuario = txtUsuario.text.toString().trim()
            val contrasena = txtContrasena.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, Activity_Login::class.java)
        startActivity(intent)
        finish()
    }
}