package com.android.login

object Member {
    var members = mutableListOf(
        MemberData("jo", "jo", "1q", "ISTJ", "25"),
        MemberData("jun", "jun", "2w", "INTJ", "27")
    )


    fun createMember(addName: String, addId: String, addPassword: String, addMbti: String, addAge: String){
        var addMember = MemberData(addName, addId, addPassword, addMbti, addAge)
        members.add(addMember)
    }

    fun updateMember(name: String, id: String, password: String, mbti: String, age: String, index: Int){
        var memberModify = MemberData(name, id, password, mbti, age)
        members[index] =memberModify
    }

    fun deleteMember(index: Int){
        members.removeAt(index)
    }

    fun retrieveMembers(): List<MemberData> {
        return members
    }
}