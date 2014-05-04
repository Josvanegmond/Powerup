package joozey.libs.powerup.graphics;

import joozey.libs.powerup.object.BatchManager;
import joozey.libs.powerup.util.DefaultColors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class DefaultSprite extends Sprite
{
	private float offsetX, offsetY;
	private float x, y;
	private String bitmapName;
	private float alpha = 1f;
	private float lightValue = 0f;
	private Sprite alphamapSprite;
	private Pixmap spritePixmap;

	public DefaultSprite( String bitmapName )
	{
		super( new Texture( Gdx.files.internal( "data/" + bitmapName ) ) );

		this.bitmapName = bitmapName;
		setDefaultValues();
	}

	public DefaultSprite( String bitmapName, Color color )
	{
		super( new Texture( Gdx.files.internal( "data/"
				+ DefaultColors.getName( color ) + "/" + bitmapName ) ) );

		this.bitmapName = bitmapName;
		setDefaultValues();
	}

	public DefaultSprite( Sprite sprite )
	{
		super( sprite );
		setDefaultValues();
	}

	@Override
	public void setColor( Color color )
	{
		if( this.bitmapName != null )
		{
			super.setTexture( new Texture( Gdx.files.internal( "data/"
					+ DefaultColors.getName( color ) + "/" + bitmapName ) ) );
		}
	}

	public void switchSprite( String bitmapName )
	{
		this.bitmapName = bitmapName;
		super.setTexture( new Texture( Gdx.files
				.internal( "data/" + bitmapName ) ) );
	}

	private void setDefaultValues()
	{
		Texture texture = this.getTexture();
		texture.setFilter( TextureFilter.Nearest, TextureFilter.Nearest );

		// set size to match aspect ratio of view
		this.setSize( texture.getWidth(), texture.getHeight() );
		this.setPosition( 0, 0 );
		this.setOrigin( texture.getWidth() / 2, texture.getHeight() / 2 );
	}

	@Override
	public final void draw( SpriteBatch batch )
	{
		super.setPosition( this.x + this.offsetX, this.y + this.offsetY );
		
		Color color = super.getColor();
		float oldAlpha = color.a;
		color.a *= alpha;

		super.setColor(color);
		super.draw( batch );

		color.a = oldAlpha;
		super.setColor(color);
	}

	public void draw()
	{
		this.draw( BatchManager.getSpriteBatch() );
	}

	@Override
	public void setPosition( float x, float y )
	{
		this.x = x;
		this.y = y;
	}

	public void dispose()
	{
		this.getTexture().dispose();
	}

	public void setOffset( float offsetX, float offsetY )
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public Vector2 getOffset()
	{
		return new Vector2( this.offsetX, this.offsetY );
	}

	public void setWidth( float width )
	{
		this.setSize( width, super.getHeight() );
	}

	public void setHeight( float height )
	{
		this.setSize( super.getWidth(), height );
	}

	public void setAlpha( float alpha )
	{
		this.alpha = alpha;
	}

	public void setLightValue( float lightValue )
	{
		this.lightValue = lightValue;
	}

}
