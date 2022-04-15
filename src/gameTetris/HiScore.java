package gameTetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class HiScore {
	private JPanel tableScore;
	private JPanel row1;
	private JPanel row2;
	private JLabel textRow1;
	private JPanel [][]panelRow2;
	private String []nguoichoi;
	private String []diem;
	private static final JLabel xephang = new JLabel("Hạng");
	private static final JLabel ten = new JLabel("Tên người chơi");
	private static final JLabel diem1 = new JLabel("Điểm");
	public void SapXep(){
		for(int i=0;i<5-1;i++){
			for(int j=i+1;j<5;j++){
				int temi = Integer.parseInt(diem[i]);
				int temj = Integer.parseInt(diem[j]);
				if(temi<temj){
					String ten = nguoichoi[i];nguoichoi[i]=nguoichoi[j];nguoichoi[j]=ten;
					String di = diem[i];diem[i]=diem[j];diem[j]=di;
				}
			}
		}
	}
	public HiScore(){
		try{
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream("hiscore.xml");
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			nguoichoi = new String[5];
			diem = new String[5];
			int i=0,j=0;
			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();
				if(event.isStartElement()){
					if(event.asStartElement().getName().getLocalPart().equals("nguoichoi")){
						event = eventReader.nextEvent();
						String s = event.asCharacters().toString();
						nguoichoi[i]=s;
						i++;
						continue;
					}
					else if(event.asStartElement().getName().getLocalPart().equals("diem")){
						event = eventReader.nextEvent();
						String s = event.asCharacters().toString();
						diem[j]=s;
						j++;
						continue;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		SapXep();
	}
	public String[] getNguoichoi(){
		return nguoichoi;
	}
	public String[] getDiem(){
		return diem;
	}
	public void write_XML(String file) throws Exception, XMLStreamException{
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(file));
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end);
		
		StartElement configStarElement = eventFactory.createStartElement("","","score");
		eventWriter.add(configStarElement);
		eventWriter.add(end);
		XMLEvent tab = eventFactory.createDTD("\t");
		//////////////////////////////////////////////
		for(int i=0;i<5;i++){
			eventWriter.add(tab);
			eventWriter.add(eventFactory.createStartElement("","","item"));
			eventWriter.add(end);
			createNode(eventWriter,"nguoichoi",nguoichoi[i]);
			createNode(eventWriter,"diem",diem[i]);
			eventWriter.add(tab);
			eventWriter.add(eventFactory.createEndElement("","","item"));
			eventWriter.add(end);
		}
		/////////////////////////////////////////////////
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndElement("","","score"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
		
	}
	public void createNode(XMLEventWriter eventWriter,String name,String value) throws XMLStreamException{
		XMLEventFactory eventFactory = XMLEventFactory.newFactory();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		StartElement sElement = eventFactory.createStartElement("","", name);
		eventWriter.add(tab);eventWriter.add(tab);
		eventWriter.add(sElement);
		Characters charracters = eventFactory.createCharacters(value);
		eventWriter.add(charracters);
		EndElement eElement = eventFactory.createEndElement("", "",name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
	public void addTenNguoiChoi(String ten,long d){
		for(int i=0;i<5;i++){
			int k = Integer.parseInt(diem[i]);
			if(d>k){
				for(int j=4;j>i;j--){
					nguoichoi[j]=nguoichoi[j-1];
					diem[j]=diem[j-1];
				}
				nguoichoi[i]=ten;
				diem[i]=Long.toString(d);
				break;
			}
		}
	}
	public JPanel showHiscore(){
		tableScore = new JPanel();
		tableScore.setBorder(new EtchedBorder());
		tableScore.setLayout(new BoxLayout(tableScore, BoxLayout.Y_AXIS));
		row1 = new JPanel();
		row1.add(new JLabel("BẢNG XẾP HẠNG"));
		row1.setBackground(Color.YELLOW);
		row1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		row2 = new JPanel(new GridLayout(6,3));
		row2.setPreferredSize(new Dimension(6*80,3*50));
		panelRow2 = new JPanel[6][3];
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<3;j++)
			{
				panelRow2[i][j] = new JPanel();
				panelRow2[i][j].setBackground(Color.WHITE);
				panelRow2[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				row2.add(panelRow2[i][j]);
			}
		}
		panelRow2[0][0].add(xephang);
		panelRow2[0][1].add(ten);
		panelRow2[0][2].add(diem1);
		
		for(int i=1;i<6;i++){
			for(int j=0;j<3;j++){
				if(j==0){
					String s = Integer.toString(i);
					JLabel h = new JLabel(s);
					panelRow2[i][j].add(h);
				}
				else if(j==1){
					JLabel lb = new JLabel(nguoichoi[i-1]);
					panelRow2[i][j].add(lb);
				}
				else{
					JLabel di = new JLabel(diem[i-1]);
					panelRow2[i][j].add(di);
				}
			}
		}
		tableScore.add(row1);
		tableScore.add(row2);
		return tableScore;
	}
}