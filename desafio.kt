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

fun geraMenu(){
    println("=================================")
    println("1- Matricular em uma Formação    ")
    println("2- Listar Formações              ")
    println("3- Detalhes de uma Formação      ")
    println("4- Sair                          ")
    println("=================================")

}

fun escolheNivel() : Nivel{
    var op : Char
    var nivel = Nivel.BASICO
    do{

        println("==================")
        println("B - Básico        ")
        println("I - Intermediário ")
        println("A - Avançado      ")
        println("==================")
        op = readLine().toString()[0]

        when(op){
            'B' -> nivel = Nivel.BASICO
            'I' -> nivel = Nivel.INTERMEDIARIO
            'A' -> nivel = Nivel.AVANCADO
        }

    }while(op != 'B' && op !='I' && op != 'A')
    return nivel
}


fun listarFormacoes(listaFormacoes : MutableList<Formacao>){
    for(formacao in listaFormacoes){
        println("\n==============================================")
        println("Formação: ${formacao.nomeFormacao}")
        println("Número de Inscritos: ${formacao.inscritos.size}")
        println(formacao.conteudos)
        println(formacao.nivel)
        println("================================================\n")
    }
}

fun buscarFormacaoPeloSeuNomeENivel(listaFormacoes: MutableList<Formacao>, nomeFormacao: String,
                                    nivelFormacao : Nivel) : Formacao? {
    var resp : Formacao? = null
    for(formacao in listaFormacoes){

        if(formacao.nomeFormacao == nomeFormacao && formacao.nivel == nivelFormacao){
            resp = formacao
            break
        }
    }
    return resp
}

fun verificaSeJaEstaInscrito(formacao: Formacao, nomeUsuario : String) : Boolean?{
    var resp : Boolean? = null
    for(usuario in formacao.inscritos){
        if(usuario.nome == nomeUsuario){
            resp = true
            break
        }
    }
    return resp
}

fun detalhaFormacao(formacao: Formacao){
    println("\n==========================================")
    println("Nome da Formação: ${formacao.nomeFormacao}")
    println("Conteúdos da formação: ${formacao.conteudos}")
    println("Nível da formação: ${formacao.nivel}")
    println("Número de inscritos na formação: ${formacao.inscritos.size}")
    println("Lista de Inscritos")
    for(inscritos in formacao.inscritos){
        println(inscritos.nome)
    }
    println("===============================================\n")

}

fun main() {
    val formacoes = mutableListOf(Formacao("Python", Nivel.BASICO),
        Formacao("Python", Nivel.INTERMEDIARIO), Formacao("Python", Nivel.AVANCADO),
        Formacao("Java", Nivel.BASICO), Formacao("Java", Nivel.INTERMEDIARIO),
        Formacao("Java", Nivel.AVANCADO), Formacao("Kotlin", Nivel.BASICO),
        Formacao("Kotlin", Nivel.INTERMEDIARIO), Formacao("Kotlin", Nivel.AVANCADO))

    var op : Char
    var parada = 0
    while(parada == 0){
        do{
            geraMenu()
            op = readLine().toString()[0]
            if(op == '1'){
                println("Python - Java - Kotlin")
                println("Informe o nome da Formação: ")
                val nomeFormacao = readLine().toString()
                println("Básico - Intermediário - Avançado")
                val nivel = escolheNivel()
                val formacaoEncontrada = buscarFormacaoPeloSeuNomeENivel(formacoes, nomeFormacao, nivel)

                println("Informe o seu nome: ->")
                val nomeUsuario = readLine().toString()
                val inscrito = formacaoEncontrada?.let { verificaSeJaEstaInscrito(it, nomeUsuario) }
                if(inscrito == true){
                    println("Você já está inscrito nesta formação.")
                }else{
                    val usuario = Usuario(nomeUsuario)
                    if (formacaoEncontrada != null) {
                        formacaoEncontrada.matricular(usuario)
                    }
                }
            }else if(op == '2') {
                listarFormacoes(formacoes)
            }else if(op == '3'){
                println("Python - Java - Kotlin")
                println("Informe o nome da formação: ")
                val nomeFormacao = readLine().toString()
                val nivelFormacao = escolheNivel()
                val formacao = buscarFormacaoPeloSeuNomeENivel(formacoes, nomeFormacao, nivelFormacao)
                if (formacao != null) {
                    detalhaFormacao(formacao)
                }
            }else if(op == '4'){
                println("Saindo...")
                parada = 3
            }
        }while(op != '1' && op != '2' && op != '3')
    }
}
