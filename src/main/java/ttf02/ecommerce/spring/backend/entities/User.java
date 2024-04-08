package ttf02.ecommerce.spring.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private String login;
	@ToString.Exclude
	private String password;
	private String name, surname;
	@Column(nullable=false, columnDefinition="BIT(1)")
	private boolean admin;
	
	public String getFullName() {
		return getName() + " " + getSurname();
	}
}
