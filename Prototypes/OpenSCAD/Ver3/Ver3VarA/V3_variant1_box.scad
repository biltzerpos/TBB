/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 3 - VARIANT 1 - OUTER ROUNDED BOX
        
        *******ATTEMPT ONE***********
        
        (Measurements in mm)
        
        
*/

$fn = 100;

////////////////// Variables ////////////////////////////

/// For Outer Cube
x_dim = 130;
y_dim = 100;
z_dim = 80;
r_dim = 30;

/// For Inner cube
x_in = x_dim - 10;
y_in = y_dim - 10;
z_in = z_dim +5;
r_in = r_dim;

// For middle slider layer
x_mid = x_in + 4;
y_mid = y_in + 8;
z_mid = 6; // height of middle slider layer 

// For bottom closing area
z_bottom = 30;

//---- For making holes for raspberry pi ---- ////

// For hole on the right side
x_side = 20;
y_side = 60;
z_side = 15;

// For hole at the back
x_back = 60;
y_back = 20;
z_back = 25;
    
    
    
/////////////// BUILDING MODEL //////////////////

//Modelling outer rounded cube
module outer_roundedcube(x_dim, y_dim, z_dim, r_dim)
hull(){
    translate([r_dim, r_dim, 0])
    {
        cylinder(r = r_dim, h = z_dim);
    }
    
    translate([x_dim - r_dim, r_dim, 0])
    {
        cylinder(r = r_dim, h = z_dim);
    }
    
    translate([r_dim, y_dim - r_dim, 0])
    {
        cylinder(r = r_dim, h = z_dim);
    }
    
    translate([x_dim - r_dim, y_dim - r_dim, 0])
    {
        cylinder(r = r_dim, h = z_dim);
    }
}
//outer_roundedcube(x_dim, y_dim, z_dim, r_dim);

//Modelling the inner rounded cube
module inner_roundedcube(x_in, y_in, z_in, r_in)
hull(){
    
    translate([5, 5, 5])
    {
        translate([r_in, r_in, 0])
        {
            cylinder(r = r_in, h = z_in);
        }
        
        translate([x_in - r_in, r_in, 0])
        {
            cylinder(r = r_in, h = z_in);
        }
        
        translate([r_in, y_in - r_in, 0])
        {
            cylinder(r = r_in, h = z_in);
        }
        
        translate([x_in - r_in, y_in - r_in, 0])
        {
            cylinder(r = r_in, h = z_in);
        }
    }
}
//inner_roundedcube(x_in, y_in, z_in, r_in);

//Make a hole in the outer cube
difference()
{
    outer_roundedcube(x_dim, y_dim, z_dim, r_dim);
    inner_roundedcube(x_in, y_in, z_in, r_in);
    
    /*// Cutting a hole for the slider to separate the layers
    translate([((x_mid - x_in)/2)+0.5, 0, (z_dim/2)])
        {
            cube([x_mid, y_mid, z_mid]);
        } */
        
    // Cutting a hole for the top lid
        translate([((x_mid - x_in)/2)+0.5, 0, z_dim - 10])
        {
            cube([x_mid, y_mid, z_mid]);
        }
        
    // Cutting a hole for bottom closing area for the top lid       
        translate([((x_in/4)- r_dim), 0, ((z_dim - (z_mid+20)))])
        {
            cube([x_mid+20, r_dim/2, (z_bottom)]);
        }
        
   // For the hole on the ride side
        /*translate([x_dim, ((y_in - y_dim) / 2), (z_in - z_dim)])
        {
            cube(x_side, y_side, z_side);
        } */
        
        
}

// Making holes for Raspberry Pi

