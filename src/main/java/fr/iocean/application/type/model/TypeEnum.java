package fr.iocean.application.type.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeEnum {
	LIVRE("Livre"),
	CD("CD"),
	DVD("DVD");
	
	String label;
}
