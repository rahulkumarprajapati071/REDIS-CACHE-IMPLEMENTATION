package in.glootech.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class Book implements Serializable{
	private Integer bookId;
	private String bookName;
	private Double bookPrice;
}
