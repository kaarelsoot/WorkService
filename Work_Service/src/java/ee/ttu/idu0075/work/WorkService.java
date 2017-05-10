/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.work;

import ee.ttu.idu0075._2015.ws.work.AddWorkerRequest;
import ee.ttu.idu0075._2015.ws.work.AddOrderRequest;
import ee.ttu.idu0075._2015.ws.work.GetOrderListRequest;
import ee.ttu.idu0075._2015.ws.work.GetOrderListResponse;
import ee.ttu.idu0075._2015.ws.work.GetOrderRequest;
import ee.ttu.idu0075._2015.ws.work.GetWorkerListRequest;
import ee.ttu.idu0075._2015.ws.work.GetWorkerListResponse;
import ee.ttu.idu0075._2015.ws.work.GetWorkerRequest;
import ee.ttu.idu0075._2015.ws.work.OrderListType;
import ee.ttu.idu0075._2015.ws.work.OrderType;
import ee.ttu.idu0075._2015.ws.work.SetOrderRequest;
import ee.ttu.idu0075._2015.ws.work.SetWorkerRequest;
import ee.ttu.idu0075._2015.ws.work.WorkerListType;
import ee.ttu.idu0075._2015.ws.work.WorkerType;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import java.math.BigInteger;

/**
 *
 * @author kaarelsoot
 */
@WebService(serviceName = "WorkService", portName = "workPort", endpointInterface = "ee.ttu.idu0075._2015.ws.work.WorkPortType", targetNamespace = "http://www.ttu.ee/idu0075/2015/ws/work", wsdlLocation = "WEB-INF/wsdl/WorkService/WorkService.wsdl")
public class WorkService {
    
    private int newWorkerID = 1;
    private int newOrderID = 1;
    static List<WorkerType> workerList = new ArrayList<WorkerType>();
    static List<OrderType> orderList = new ArrayList<OrderType>();

    public WorkerType getWorker(GetWorkerRequest parameter) {
        WorkerType worker = null;
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            for (int x=0; x<workerList.size(); x++) {
                if (workerList.get(x).getWorkerID() == parameter.getWorkerID()) {
                    worker = workerList.get(x);
                }
            }
        }
        return worker;
    }

    public WorkerType addWorker(AddWorkerRequest parameter) {
        WorkerType worker = new WorkerType();
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            worker.setName(parameter.getName());
            worker.setOccupation(parameter.getOccupation());
            worker.setAvailability(parameter.getAvailability());
            worker.setHourlyRate(parameter.getHourlyRate());
            worker.setWorkerID(BigInteger.valueOf(newWorkerID++));
            workerList.add(worker);
        }
        return worker;
    }

    public WorkerType setWorker(SetWorkerRequest parameter) {
        BigInteger param = workerList.get(0).getWorkerID();
        WorkerType worker = new WorkerType();
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            int x = 0;
            for (x = 0; x<workerList.size(); x++) {
                if (workerList.get(x).getWorkerID().equals(parameter.getWorkerID())) {
                    worker = workerList.get(x);
                    break;
                }
            }
            // Name
            String name = worker.getName();
            if (parameter.getName().isEmpty()){
                worker.setName(name);
            }
            else {
                worker.setName(parameter.getName());
            }
            // Occupation
            String occupation = worker.getOccupation();
            if (parameter.getOccupation().isEmpty()){
                worker.setOccupation(occupation);
            }
            else {
                worker.setOccupation(parameter.getOccupation());
            }
            // Hourly Rate
            BigInteger hourlyRate = worker.getHourlyRate();
            if (parameter.getHourlyRate() == null) {
                worker.setHourlyRate(hourlyRate);
            }
            else {
                worker.setHourlyRate(parameter.getHourlyRate());
            }
            worker.setAvailability(parameter.getAvailability());
            worker.setWorkerID(workerList.get(x).getWorkerID());
            //wt.setHourlyRate(parameter.getHourlyRate());
        }
        return worker;
    }//

    public GetWorkerListResponse getWorkerList(GetWorkerListRequest parameter) {
        GetWorkerListResponse workerlist = new GetWorkerListResponse();
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            for (WorkerType worker : workerList) {
                workerlist.getWorker().add(worker);
            }
        }
        return workerlist;
    }

    public OrderType getOrder(GetOrderRequest parameter) {
        OrderType order = null;
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            for (int x=0; x<orderList.size(); x++) {
                if (orderList.get(x).getOrderID() == parameter.getOrderID()) {
                    order = orderList.get(x);
                }
            }
        }
        return order;
    }

    public OrderType addOrder(AddOrderRequest parameter) {
        OrderType order = new OrderType();
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            order.setWorkerID(parameter.getWorkerID());
            order.setStartDate(parameter.getStartDate());
            order.setEndDate(parameter.getEndDate());
            order.setStatus(parameter.getStatus());
            order.setOrderID(BigInteger.valueOf(newOrderID++));
            orderList.add(order);
        }
        return order;
    }

    public OrderType setOrder(SetOrderRequest parameter) {
        OrderType order = new OrderType();
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            
        }
        return null;
    }

    public GetOrderListResponse getOrderList(GetOrderListRequest parameter) {
        GetOrderListResponse orderlist = new GetOrderListResponse();
        if (parameter.getToken().equalsIgnoreCase("nugis")) {
            for (OrderType order : orderList) {
                orderlist.getOrder().add(order);
            }
        }
        return orderlist;
    }
    
}
