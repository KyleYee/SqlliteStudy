package com.example.yunnnn.sqllitestudy;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by yunnnn on 2016/10/9.
 */

public class SAXParser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            //获取AssetManager管理器对象
            AssetManager as = this.getAssets();
            //通过AssetManager的open方法获取到beauties.xml文件的输入流
            InputStream is = as.open("student.xml");
            InputSource is2 = new InputSource(is);
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = parserFactory.newSAXParser();

            XMLReader reader = parser.getXMLReader();
            SAXParserHolder handler = new SAXParserHolder();
            reader.setContentHandler(handler);
//            InputSource is = new InputSource(this.getClassLoader().getResourceAsStream("student.xml"));//取得本地xml文件
//            reader.parse(is);
            reader.parse(is2);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    class SAXParserHolder extends DefaultHandler {

        private List<Person> datalist;
        private Person person;

        public SAXParserHolder() {
            datalist = new ArrayList<>();
            person = null;
        }

        @Override
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(locator);
            Log.e("setDocumentLocator:", "setDocumentLocator");

        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            Log.e("startDocument:", "startDocument");
            person = new Person();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            Log.e("endDocument:", "endDocument");

        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            super.startPrefixMapping(prefix, uri);
            Log.e("startPrefixMapping:", "startPrefixMapping");
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            super.endPrefixMapping(prefix);
            Log.e("endPrefixMapping:", "endPrefixMapping");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            Log.e("startElement:", "uri:" + uri + "localName:" + localName + "qName:" + qName);
         /*   if (localName.equals("person")) {
                person.setId(Integer.valueOf(qName));
            }

            if (localName.equals("name")) {
                person.setName(qName);
            }

            if (localName.equals("age")) {
                person.setAge(Short.valueOf(qName));
            }*/

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            Log.e("endElement:", "endElement");
//            datalist.add(person);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            Log.e("characters:", new String(ch, start, length));
        }
    }


    class Person {
        private Integer id;
        private String name;
        private Short age;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Short getAge() {
            return age;
        }

        public void setAge(Short age) {
            this.age = age;
        }
    }
}
