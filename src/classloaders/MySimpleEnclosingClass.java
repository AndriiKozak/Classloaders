/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

import java.util.Objects;

/**
 *
 * @author Andrii_Kozak1
 */
public class MySimpleEnclosingClass {

    MyClass myClass;

    public MySimpleEnclosingClass(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.myClass);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MySimpleEnclosingClass other = (MySimpleEnclosingClass) obj;
        if (!Objects.equals(this.myClass, other.myClass)) {
            return false;
        }
        return true;
    }

}
