/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrii_Kozak1
 */
package classloaders;

public class MySubClass extends MyClass {

    int otherField;

    public MySubClass(int field, int otherField) {
        super(field);
        this.otherField = otherField;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 19 * hash + this.otherField;
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
        if (!super.equals(obj)) {
            return false;
        }
        final MySubClass other = (MySubClass) obj;
        if (this.otherField != other.otherField) {
            return false;
        }
        return true;
    }

}
