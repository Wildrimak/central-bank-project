package br.com.infoway.cashmachine.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class Error {

	private Integer status;
	private LocalDateTime date;
	private String message;
	private List<Field> fields;

	public Error(Integer status, String message) {
		this.status = status;
		this.date = LocalDateTime.now();
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public static class Field {

		private String entity;
		private String field;
		private String motive;

		public Field(String entity, String field, String motive) {
			this.entity = entity;
			this.field = field;
			this.motive = motive;
		}

		public String getEntity() {
			return entity;
		}

		public void setEntity(String entity) {
			this.entity = entity;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getMotive() {
			return motive;
		}

		public void setMotive(String motive) {
			this.motive = motive;
		}
	}

}
