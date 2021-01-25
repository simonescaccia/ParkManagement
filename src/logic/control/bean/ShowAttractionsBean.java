package logic.control.bean;

import logic.exception.FilterValueErrorException;
import logic.exception.OrderValueErrorException;

public class ShowAttractionsBean {
	
	private String filter;
	private String order;
	private PositionBean positionBean;
	
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) throws FilterValueErrorException {
		if(validateFilter(filter)) {
			this.filter = filter;
		} else {
			throw new FilterValueErrorException("Filter value error");
		}
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) throws OrderValueErrorException {
		if((order == null) || (order.equals("distance")) || (order.equals("waitingtime"))) {
			this.order = order;
		} else {
			throw new OrderValueErrorException("Order value error");
		}
	}
	public PositionBean getPositionBean() {
		return positionBean;
	}
	public void setPositionBean(PositionBean positionBean) {
		this.positionBean = positionBean;
	}
	
	private boolean validateFilter(String filter) {
		if(filter == null) {
			return true;
		}
		String[] values = {"Fun", "Photo Store", "Restaurant", "Shop", "Toilet"};
		int i;
		for(i=0; i<values.length; i++) {
			if(filter.equals(values[i])) {
				return true;
			}
		}
		return false;
	}
}
