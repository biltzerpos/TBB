/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 3 - VARIANT 4 - OUTER ROUNDED BOX
        
        *******ATTEMPT THREE ***********
        
        (Measurements in mm)
        
        
*/


/////////////////// IMPORT FILES /////////////////

include<pi_post.scad>;
include<V3_Variant4_lid_subv1.scad>;

////////////////// VARIABLES ////////////////////////////
$fn = 200;

// For outer rounded cube
x_out = 180;
y_out = 120;
z_out = 80;
r_out = 30;

// For inner cube
x_in = x_out - 50;
y_in = y_out - 30;
z_in = z_out + 50; 

// For Raspberry Pi mount
hole_rad = 1.2; // Actual rad = 1.375
hole_h = 5;

pi_mount_h = 5;
pi_mount_rad = 3;
y_dist_mount = 58;
x_dist_mount = 49;

//// FOR RASPBERRY PI HOLES ////

//For Right side hole
x_side = 50;
y_side = 60;
z_side = 20;

// For hole at the back
x_back = 60;
y_back = 50;
z_back = 20;

// For slider
r_front = r_out - 10;



////////////////// MODULES //////////////////////////////

// Modelling outer rounded cube
module outer_cube(x_out, y_out, z_out, r_out)
hull(){
    translate([r_out, r_out, r_out])
    {
        sphere(r = r_out);
    }
    
    translate([x_out - r_out, r_out, r_out])
    {
        sphere(r = r_out);
    }
    
    translate([r_out, y_out - r_out, r_out])
    {
        sphere(r = r_out);
    }
    
    translate([x_out - r_out, y_out - r_out, r_out])
    {
        sphere(r = r_out);
    }
    
    translate([r_out, r_out, z_out - r_out])
    {
        sphere(r = r_out);
    }
    
    translate([x_out - r_out, r_out, z_out - r_out])
    {
        sphere(r = r_out);
    }
    
    translate([r_out, y_out - r_out, z_out - r_out])
    {
        sphere(r = r_out);
    }
    
    translate([x_out - r_out, y_out - r_out, z_out - r_out])
    {
        sphere(r = r_out);
    }
}

//Modelling inner cube
module inner_cube(x_in, y_in, z_in)
{
    translate([((x_out/2)-(x_in/2)), ((y_out/2)-(y_in/2)), r_out/2])
    {
        cube([x_in, y_in, z_in]);
    }
}

// Modelling right and back hole
module side_hole(x_side, y_side, z_side)
{
    translate([(x_in + (x_out - x_in)/2), ((y_out - y_in)/2), (r_out/2)+pi_mount_h])
    {
        cube([x_side, y_side, z_side]);
    }
}

module back_hole(x_back, y_back, z_back)
{
    translate([(((x_out - x_in)/2) + (x_in - x_back)), (((y_out - y_in)/2)+ y_in), (r_out/2)+pi_mount_h])
    {
        cube([x_back, y_back, z_back]);
    }
}

/****************** BUILDING THE MODEL **************/
difference()
{
    outer_cube(x_out, y_out, z_out, r_out);
    inner_cube(x_in, y_in, z_in);
    side_hole(x_side, y_side, z_side);
    back_hole(x_back, y_back, z_back);
    
    #translate([0, -r_out/4, (1.3)*r_out])v3_var4_lid_sv1();
    #translate([((x_out/2)-(x_in/2)),0, z_out - r_out])cube([x_in, 20, r_out]);
}

#translate([x_in - x_dist_mount+(x_out-x_in)/4,y_in - y_dist_mount+((y_out-y_in)/4)-27,r_out/2])
{
    pi_post(pi_mount_rad, pi_mount_h, hole_rad, hole_h);
}