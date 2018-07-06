package com.fundoonotes.model;

public class GenericTestClass<T> {
	private T header;
	private T properties;
	private T payload;
	private T footer;

	public T getHeader() {
		return header;
	}

	public void setHeader(T header) {
		this.header = header;
	}

	public T getProperties() {
		return properties;
	}

	public void setProperties(T properties) {
		this.properties = properties;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public T getFooter() {
		return footer;
	}

	public void setFooter(T footer) {
		this.footer = footer;
	}

}
