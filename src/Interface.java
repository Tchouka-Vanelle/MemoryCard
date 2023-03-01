
public class Interface {

	int face;
	
	Interface(int face)
	{
		this.face=face;
	}
	
	/*void changement(int x, int y)
	{
		if(this.face==2)
			this.face=1;
		if(this.face==1)
			this.face=2;
	}*/

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}
	
	
}
