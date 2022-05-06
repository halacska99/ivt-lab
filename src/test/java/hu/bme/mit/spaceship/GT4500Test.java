package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPRI;
  private TorpedoStore mockSEC;  

  @BeforeEach
  public void init(){
    mockPRI = mock(TorpedoStore.class);
    mockSEC = mock(TorpedoStore.class);  
    this.ship = new GT4500(mockPRI, mockSEC);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPRI.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE); 

    // Assert
    verify(mockPRI, times(1)).fire(1); 
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    when(mockPRI.fire(1)).thenReturn(true);
    when(mockSEC.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.ALL); 

    // Assert
   }

  @Test
  public void fireTorpedo_Single_pri(){
    when(mockPRI.isEmpty()).thenReturn(true);
    when(mockSEC.fire(1)).thenReturn(true); 

      // Act
      ship.fireTorpedo(FiringMode.SINGLE); 

      // Assert
      verify(mockPRI, times(1)).isEmpty();
      verify(mockSEC, times(1)).fire(1);  
  }

  @Test
  public void fireTorpedo_Single_pri_fail(){
    when(mockPRI.isEmpty()).thenReturn(true);
    when(mockSEC.isEmpty()).thenReturn(true); 

      // Act
      ship.fireTorpedo(FiringMode.SINGLE); 

      // Assert
      verify(mockPRI, times(1)).isEmpty();
      verify(mockSEC, times(1)).isEmpty();  
  }

  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange

    when(mockPRI.isEmpty()).thenReturn(true);
    when(mockSEC.isEmpty()).thenReturn(true); 
    // Act
    ship.fireTorpedo(FiringMode.ALL); 

    // Assert
    verify(mockPRI, times(1)).isEmpty(); 
    verify(mockSEC, times(1)).isEmpty(); 
  }

  @Test
  public void fireTorpedo_Single_pri_re(){
    // Arrange
    when(mockPRI.fire(1)).thenReturn(true);
    when(mockSEC.isEmpty()).thenReturn(true);
    
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE); 

    // Assert
    verify(mockPRI, times(2)).fire(1); 
    verify(mockSEC, times(1)).isEmpty();  
  }  

  @Test
 public void fireTorpedo_Single_sec(){
   // Arrange
   when(mockSEC.fire(1)).thenReturn(true);
   when(mockPRI.isEmpty()).thenReturn(true);
   when(mockSEC.fire(1)).thenReturn(true);
   
   // Act
   ship.fireTorpedo(FiringMode.SINGLE); 

   // Assert
   verify(mockSEC, times(1)).fire(1); 
   verify(mockSEC, times(1)).fire(1);
 } 

 @Test
 public void fireTorpedo_Single_sec_re(){
   // Arrange
   when(mockPRI.fire(1)).thenReturn(true);
   when(mockSEC.fire(1)).thenReturn(true);
   when(mockSEC.isEmpty()).thenReturn(false);  
   
   // Act
   ship.fireTorpedo(FiringMode.SINGLE);
   ship.fireTorpedo(FiringMode.SINGLE); 

   // Assert
   verify(mockPRI, times(1)).fire(1); 
   verify(mockSEC, times(1)).fire(1);  
 }  
}  



