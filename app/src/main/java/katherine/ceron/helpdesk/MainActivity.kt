package katherine.ceron.helpdesk

import RecyclerViewHelpers.Adaptador
import RecyclerViewHelpers.Ticket
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import katherine.ceron.helpdesk.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.Conexion
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val txtNumeroT = findViewById<TextView>(R.id.txtNumeroT)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtAutor = findViewById<TextView>(R.id.txtAutor)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val txtEstado = findViewById<TextView>(R.id.txtEstado)
        val btnAgregarT = findViewById<Button>(R.id.btnAgregarT)
        val rcvTicket = findViewById<RecyclerView>(R.id.rcvTicket)

        rcvTicket.layoutManager = LinearLayoutManager(this)

        fun obtenerTicket() : List<Ticket>{

            val  objConexion = Conexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM Ticket")

            val listaTicket = mutableListOf<Ticket>()

            while (resultSet.next()){

                val uuid = resultSet.getString("uuid")
                val Numero_Ticket = resultSet.getInt("Numero_Ticket")
                val Titulo_Ticket = resultSet.getString("Titulo_Ticket")
                val Descripion_Ticket = resultSet.getString("Descripion_Ticket")
                val Autor_Ticket = resultSet.getString("Autor_Ticket")
                val Email_Autor  = resultSet.getString("Email_Autor")
                val Estado_Ticket = resultSet.getString("Estado_Ticket")

                val valoresJuntos = Ticket(uuid,Numero_Ticket,Titulo_Ticket,Descripion_Ticket,Autor_Ticket,Email_Autor,Estado_Ticket)

                listaTicket.add(valoresJuntos)

            }

            return  listaTicket
        }

        CoroutineScope(Dispatchers.IO).launch {

            val TicketDB = obtenerTicket()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(TicketDB)
                rcvTicket.adapter = adapter
            }
        }



        btnAgregarT.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val objConexion = Conexion().cadenaConexion()

                val addTicket = objConexion?.prepareStatement("insert into Ticket (uuid,Numero_Ticket, Titulo_Ticket, Descripion_Ticket, Autor_Ticket, Email_Autor, Estado_Ticket) values(?, ?, ?, ?, ?, ?, ?) ")!!
                addTicket.setString(1,UUID.randomUUID().toString())
                addTicket.setInt(2,txtNumeroT.text.toString().toInt())
                addTicket.setString(3,txtTitulo.text.toString())
                addTicket.setString(4,txtDescripcion.text.toString())
                addTicket.setString(5,txtAutor.text.toString())
                addTicket.setString(6,txtEmail.text.toString())
                addTicket.setString(7,txtEstado.text.toString())
                addTicket.executeUpdate()
            }
        }

    }
}