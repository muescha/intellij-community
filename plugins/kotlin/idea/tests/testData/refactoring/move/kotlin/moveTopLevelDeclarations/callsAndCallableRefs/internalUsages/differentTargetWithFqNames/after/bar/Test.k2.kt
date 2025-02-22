package bar

import foo.A.Companion.companionExtensionMember
import foo.O.objectExtensionMember1
import foo.X
import foo.classExtension
import foo.companionExtension
import foo.objectExtension
import foo.topLevel

fun test() {
    foo.A().classMember()
    foo.A().classExtension()
    foo.O.objectMember1()
    foo.O.objectMember2()
    foo.O.objectExtension()
    foo.A.companionMember()
    foo.A.companionExtension()
    foo.J().javaClassMember()
    foo.J.javaClassStaticMember()
    foo.topLevel()
    with(foo.O) { 1.objectExtensionMember1() }
    1.objectExtensionMember2()
    with(foo.A) { 1.companionExtensionMember() }

    foo.A()::classMember
    foo.A::classMember
    foo.A()::classExtension
    foo.A::classExtension
    foo.O::objectMember1
    foo.O::objectMember2
    foo.O::objectExtension
    foo.A.Companion::companionMember
    (foo.A)::companionMember
    foo.A.Companion::companionExtension
    (foo.A)::companionExtension
    foo.J()::javaClassMember
    foo.J::javaClassMember
    foo.J::javaClassStaticMember
    ::topLevel
    ::X
    ::XConstr
    X::XX
    ::J
    ::JConstr
    J::JJ

    with(foo.A()) {
        classMember()
        this.classMember()
        classExtension()
        this.classExtension()

        this::classMember
        this::classExtension
    }

    with(foo.J()) {
        javaClassMember()
        this.javaClassMember()

        this::javaClassMember
    }
}