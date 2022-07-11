1.Transmission Tab:
1.1 when display transmission list:
foreach(Tranmission t: invoiceTransmissionList) {
    if(t.displayInvoiceStatus != "G") {
        if(not the last record) {
            //display (R)
        }
    } else {
            //display t.displayInvoiceStatus
    }
}
1.2 when display Invoice# link, use if expression: 
    if(invoiceSummary.preloaded_ind == 0) {
        view-invoice-cover-detail.do?method=displayCoverTab&invoiceID=?
    }
1.3 when display createdby
foreach(Tranmission t: invoiceTransmissionList) {
    if(displayInvoiceStatus != "R") {
        //display "System"
    } else {
    //display t.creation_user_nam
    }
}
1.4 invoice amount:
foreach(Tranmission t: invoiceTransmissionList) {
    if(t.displayInvoiceStatus!='G') {
    //display invoiceSummary.total_invoice_amt
}
1.5 Open amount:
foreach(Tranmission t: invoiceTransmissionList) {
    if(t.displayInvoiceStatus!='G') {
    //display invoiceSummary.outstandingamount
}

2. Transaction Tab:
    summary:
    2.1 Original invoice amount: total_invoice_amt
    2.2 Current invoice amount: total_invoice_amt + total_adj_amt
    2.3 Open amount: total_invoice_amt + total_adj_amt+ total_payment_amt
    
    display list:
    2.4 Created By
    foreach(Transaction t: invoiceTransactionList) {
        if(t.transactionType!= 'Adjustment') {
           //display "System"
          } else {
             // display t.creation_user_nam
           }
       }
    2.5 amount:
         trans_amt
    2.6 OpenAmout
    2.7 transaction source:
         reference_num