package com.project.tb.payload;

public class FindTicketsByEmailResponse {
private BookRequests bookRequest;
private Long QRCodeId;
public BookRequests getBookRequest() {
	return bookRequest;
}
public void setBookRequest(BookRequests bookRequest) {
	this.bookRequest = bookRequest;
}
public Long getQRCodeId() {
	return QRCodeId;
}
public void setQRCodeId(Long qRCodeId) {
	QRCodeId = qRCodeId;
}
}
