/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 2
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 2 - TOP LID SLIDER 
        
        (Measurements in mm)
        
*/

// Modelling the top lid slider
module slider_layer()
{
    
    difference()
    {
        cube([140, 148, 3]);  
    
        // Adding a hole for the braille cell
        translate([66.75, 50, 0])
        {
            cube([7, 29, 3]);
        }
        
        // Adding hole for button 1
        translate([14.2, 15, 0])
        {
            cube([15.8, 15.8, 3]);
        }
        
        // Adding hole for button 2
        translate([44.2, 15, 0])
        {
            cube([15.8, 15.8, 3]);
        }
        
        // Adding hole for button 3
        translate([80, 15, 0])
        {
            cube([15.8, 15.8, 3]);
        }
        
        // Adding hole for button 4
        translate([110, 15, 0])
        {
            cube([15.8, 15.8, 3]);
        }
    }
    
    // Adding the handle
    translate([55, -20, 0])
    {
        cube([30, 20, 3]);
    }
    
    // Adding the bottom closing area
    translate([0, 0, -43])
    {
        cube([140, 3 ,43]);
    }
}
slider_layer();

