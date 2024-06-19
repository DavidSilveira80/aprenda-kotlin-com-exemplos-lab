data class Usuario(val nome: String)


open class ConteudoEducacional(nomeFormacao: String){

    val conteudos : List<String>

    init{
        conteudos = obterConteudos(nomeFormacao)
    }


    private fun obterConteudos(nome:String): List<String>{
        return when(nome){
            "Python" -> listOf("PEP8", "Python Fundamentos", "Django")
            "Java" -> listOf("Java Fundamentos", "Spring Framework")
            "Kotlin" -> listOf("Kotlin fundamentos", "Kotlin POO", "Android Studio")
            else -> listOf()
        }
    }

}

class Formacao(val nomeFormacao: String): ConteudoEducacional(nomeFormacao){

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        println("Matriculando Usuário:${usuario.nome}, na Formação: ${nomeFormacao} com os Conteúdos: ${conteudos}")
        inscritos.add(usuario)

    }
}

fun main() {

    val user = Usuario("David")
    val formacao = Formacao("Kotlin")
    println(formacao.nomeFormacao)
    formacao.matricular(user)






}
