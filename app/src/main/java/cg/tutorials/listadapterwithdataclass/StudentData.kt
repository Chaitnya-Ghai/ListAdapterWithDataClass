package cg.tutorials.listadapterwithdataclass

data class StudentData(
    var rollNo:Int,
    var name:String,
    var subject:String
){
    override fun toString(): String {
        return "$rollNo\n $name $subject"
    }
}
