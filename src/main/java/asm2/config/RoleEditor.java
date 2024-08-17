package asm2.config;

import asm2.entity.Role;
import org.springframework.beans.propertyeditors.PropertiesEditor;

public class RoleEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Role role = new Role();
        role.setId(Integer.parseInt(text)); // Assuming Role has an 'id' property
        // You might need additional logic to fetch the role from database based on 'id'
        setValue(role);
    }
}
