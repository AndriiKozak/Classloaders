/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classloaders;

/**
 *
 * @author Andrii_Kozak1
 */
public class MyClass {
    private int field;


    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    
    public MyClass(int field) {
        this.field = field;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.field;
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
        final MyClass other = (MyClass) obj;
        if (this.field != other.field) {
            return false;
        }
        return true;
    }
    
}
