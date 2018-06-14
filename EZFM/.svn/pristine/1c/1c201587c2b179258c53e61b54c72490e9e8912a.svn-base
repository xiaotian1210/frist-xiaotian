package com.shareworx.ezfm.webservice.fm.out;


import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
public class TestService {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//        final String address = "http://192.168.0.82:8080/archibus/cxf/BasisService";
        final String address = "http://101.201.196.210:8081/archibus/cxf/BasisService";
        factory.setAddress(address);
        factory.setServiceClass(IBasisDataService.class);
        IBasisDataService hw = (IBasisDataService)factory.create();
//        String str = hw.getValue("你好");
        String str = hw.getBasisDateList("V_DMS_EQ", "2016-05-24 10:04:44.903", 1);
        System.out.println(str);
        
    }
    
}
