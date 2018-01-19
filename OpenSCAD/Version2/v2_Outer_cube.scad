/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 2
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 2 - OUTER BOX
        
        (Measurements in mm)
        
        
*/
// Modelling the outer cube
module outer_cube()
{
    // Making a hole in the cube
    difference()
    {
        cube([150, 150, 120]);
        translate([10, 10, 10])
        {
            cube([130, 130, 130]);
        }
        
        // Cutting a hole for the slider to separate the layers
        translate([2, 0, 60])
        {
            cube([144, 148, 7]);
        }
        
        // Cutting a hole for the top lid
        translate([2, 0, 110])
        {
            cube([144, 148, 7]);
        }
        
        // Cutting a hole on the right for HDMI and USB
        translate([140, 10, 10])
        {
            cube([10, 50, 25]);
        }
        
        // Cutting a hole in the back for USB ports and ethernet cable
        translate([98, 140, 10])
        {
            cube([42, 45, 30]);
        }
    }    
}
outer_cube();
 
          


