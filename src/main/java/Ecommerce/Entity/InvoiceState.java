package Ecommerce.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class InvoiceState {
	private BigDecimal ID;
	private String name;
	private String description;
	private Timestamp createAt;
	private Timestamp updateAt;

	public BigDecimal getID() {
		return ID;
	}

	public void setID(BigDecimal ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public InvoiceState(BigDecimal ID, String name, String description, Timestamp createAt, Timestamp updateAt) {
		super();
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public InvoiceState() {
		super();
	}
}
