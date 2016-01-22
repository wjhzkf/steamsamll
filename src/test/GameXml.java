package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import bean.Game;

public class GameXml {
	public List games = new ArrayList<Game>();
	
	public List getAllGames() throws JDOMException, IOException{
		Game oneGame;
		File file = new File("conf\\TopGame.xml");
		
		SAXBuilder sb = new SAXBuilder();		//实例化
		Document doc = sb.build(file);			//构造文档元素
		Element root = doc.getRootElement();	//获取根元素
		List list = root.getChildren("Game");	//读取孩子元素
		
		for(int i=0;i<20&&i<list.size();i++){
			Element game = (Element)list.get(i);
			String id = game.getAttributeValue("id");
			String name = game.getChildText("name");
			String remark = game.getChildText("remark");
			String link = game.getChildText("link");
			String xpath = game.getChildText("xpath");
			String img = game.getChildText("img");
			
			oneGame = new Game();
			oneGame.setId(id);
			oneGame.setName(name);
			oneGame.setRemark(remark);
			oneGame.setLink(link);
			oneGame.setXpath(xpath);
			oneGame.setImg(img);
			
			games.add(oneGame);	
		}
		
		return games;
		
	}
}
