package com.tuling.springcloud.stock.设计模式.创建模式.工厂方法模式;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class 测试 {
    /**
     * 该测试采用了一种通过读取配置文件创建工厂并产生产品的模式
     * 这样可以对开发者完全隐藏产品的创建过程
     *
     * 工厂方法的模式为
     * 一个产品一个工厂，由实例化一个工厂的同时，由这个被实例化的工厂取创建一个具体的产品
     * 所有的产品都有一个顶层接口，所有的工厂也有一个顶层的接，一个产品对应一个工厂
     */
    @Test
    public void ddddd() throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //----------------------------------
        Factory f1 = new Factory1();
        Factory f2 = new Factory2();
        Product product1 = f1.createProduct();
        Product product2 = f2.createProduct();
        product1.showProduct();
        product2.showProduct();
        //----------------------------------

        //创建DOM文档对象
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dFactory.newDocumentBuilder();
        Document doc = builder.parse(new File("src\\main\\resources\\ff.xml"));

        //获取包含类名的文本节点
        NodeList nl = doc.getElementsByTagName("className");
        Node classNode=nl.item(0).getFirstChild();
        String cName=classNode.getNodeValue();
        Class aClass = Class.forName(cName);
        Factory factory = (Factory) aClass.newInstance();
        Product product = factory.createProduct();
        product.showProduct();
    }
}
