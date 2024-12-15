package com.wxy.springbackend.repository;

import com.wxy.springbackend.model.OrderProcess;
import org.hibernate.query.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderProcessRepositroy {
    private final JdbcTemplate jdbcTemplate;

    public OrderProcessRepositroy(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String payment(int ticketid){
        String sql = """
                SELECT
                    invoice_id AS invoice_id,
                    payment_state AS PaymentState,
                    valid_state AS ValidState
                FROM
                    wxy_invoice
                WHERE
                    ticket_id = ?;
                """;
        OrderProcess order = jdbcTemplate.query(sql, new Object[]{ticketid},
            (ResultSet rs) ->{
                OrderProcess orderProcess = new OrderProcess();
                if(rs.next()){
                    orderProcess.setTicketId(ticketid);
                    orderProcess.setInvoiceId(rs.getInt("invoice_id"));
                    orderProcess.setPaymentState(rs.getString("PaymentState"));
                    orderProcess.setValidState(rs.getString("ValidState"));
                }
                return orderProcess;
            }
        );

//        System.out.println(order);

        if(order.getValidState().equals("refund")){
            return "payment failure: ticket has been cancelled";
        }else if(order.getPaymentState().equals("true")){
            return "payment failure: already paid";
        }else{
            jdbcTemplate.update("""
                    UPDATE wxy_invoice
                    SET
                        payment_state = 'true',
                        valid_state = 'valid'
                    WHERE
                        invoice_id = ?;
                    """,
                    order.getInvoiceId());
            return "payment success!";
        }

    }

}
