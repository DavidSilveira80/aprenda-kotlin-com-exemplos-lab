data class Usuario(val nome : String)

enum class Nivel {BASICO, INTERMEDIARIO, AVANCADO}

open class ConteudoEducacional(nomeFormacao: String){

    val conteudos : List<String>

    init{
        conteudos = obterConteudos(nomeFormacao)
    }

    private fun obterConteudos(nome:String): List<String>{
        return when(nome){
            "Python" -> listOf("PEP8", "Python Fundamentos", "Django")
            "Java" -> listOf("Java Fundamentos", "Spring Framework")
            "Kotlin" -> listOf("Kotlin Fundamentos", "Kotlin POO", "Android Studio")
            else -> listOf()
        }
    }
}

class Formacao(val nomeFormacao : String, val nivel : Nivel) : ConteudoEducacional(nomeFormacao){

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario : Usuario) {
        println("Matriculando Usuário:${usuario.nome}, na Formação: ${nomeFormacao} com os Conteúdos: ${conteudos}" +
                " no Nível: ${nivel}")
        inscritos.add(usuario)

    }
}

fun main() {

    val user1 = Usuario("David")
    val user2 = Usuario("Daniel")

    val conteudos = ConteudoEducacional("Java")
    println(conteudos.conteudos)

    val formacao1 = Formacao("Kotlin", Nivel.INTERMEDIARIO)

    println(formacao1.nomeFormacao)
    formacao1.matricular(user1)
    formacao1.matricular(user2)
    println(formacao1.inscritos)

    val formacao2 = Formacao("Java", Nivel.AVANCADO)

    formacao2.matricular(user1)
    println(formacao2.inscritos)

    val formacao3 = Formacao("Python", Nivel.BASICO)
    formacao3.matricular(user2)
    println(formacao3.inscritos)
}

