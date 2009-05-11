package de.felixbruns.jotify.media;

import java.util.ArrayList;
import java.util.List;

import de.felixbruns.jotify.util.XMLElement;

public class Album {
	private String      id;
	private String      name;
	private Artist      artist;
	private String      cover;
	private float       popularity;
	private List<Track> tracks;
	
	private Album(){
		this(null, null, null);
	}
	
	public Album(String id, String name, Artist artist){
		this.id         = id;
		this.name       = name;
		this.artist     = artist;
		this.cover      = null;
		this.popularity = Float.NaN;
		this.tracks     = new ArrayList<Track>();
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Artist getArtist(){
		return this.artist;
	}
	
	public String getCover(){
		return this.cover;
	}
	
	public float getPopularity(){
		return this.popularity;
	}
	
	public List<Track> getTracks(){
		return this.tracks;
	}
	
	public static Album fromXMLElement(XMLElement albumElement){
		Album album = new Album();
		
		/* Set id. */
		if(albumElement.hasChild("id")){
			album.id = albumElement.getChildText("id");
		}
		
		/* Set name. */
		if(albumElement.hasChild("name")){
			album.name = albumElement.getChildText("name");
		}
		
		/* Set artist. */
		if(albumElement.hasChild("artist-id") && (albumElement.hasChild("artist") || albumElement.hasChild("artist-name"))){
			album.artist = new Artist(
				albumElement.getChildText("artist-id"),
				albumElement.hasChild("artist")?albumElement.getChildText("artist"):albumElement.getChildText("artist-name")
			);
		}
		
		/* Set cover. */
		if(albumElement.hasChild("cover")){
			String value = albumElement.getChildText("cover");
			
			if(!value.isEmpty()){
				album.cover = value;
			}
		}
		
		/* Set popularity. */
		if(albumElement.hasChild("popularity")){
			album.popularity = Float.parseFloat(albumElement.getChildText("popularity"));
		}
		
		/* Set tracks. */
		if(albumElement.hasChild("discs")){
			for(XMLElement discElement : albumElement.getChild("discs").getChildren("disc")){
				for(XMLElement trackElement : discElement.getChildren("track")){
					album.tracks.add(Track.fromXMLElement(trackElement));
				}
			}
		}		
		
		/* TODO: album-type, copyright, discs, ... */
		
		return album;
	}
	
	public boolean equals(Object o){
		if(o instanceof Album){
			Album a = (Album)o;
			
			return this.id.equals(a.id);
		}
		
		return false;
	}
	
	public int hashCode(){
		return (this.id != null) ? this.id.hashCode() : 0;
	}
}
