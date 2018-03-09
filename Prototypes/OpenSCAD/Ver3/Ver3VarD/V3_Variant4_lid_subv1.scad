/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3 - VARIANT 4
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 3_VARIANT 4_LID_SUB-VERSION 1
        
        *******ATTEMPT THREE ***********
        
        (Measurements in mm)
        
        
*/

/////////////////// IMPORT FILES /////////////////
use<unicode_braille_alpha.scad>;


////////////////// VARIABLES ////////////////////////////
$fn = 200;

// For outer rounded cube
x_out = 180;
y_out = 120;
z_out = 80;
r_out = 30;

r_front = r_out - 10;
r_cut = r_front;

// For inner cube
x_in = x_out - 50;
y_in = y_out - 30;
z_in = z_out + 50;

// For top slider layer
x_slid = x_out - r_out;
y_slid = y_in + 10;
z_slid = 23; // height of top slider layer
r_slid = r_front;


// For braille cell
x_cell = 6.3;
y_cell = 28.06;
z_cell = 5;

// Gap between bump and braille cell
gap = 5;

// For button plate
x_bplate = x_out - 2*r_front;
y_bplate = 20;
z_bplate = 2*r_front;

// For buttons
x_but = 15.8;
y_but = 3*r_front;
z_but = 15.8;

// For guide line
x_guide = x_slid*0.4;
y_guide = y_slid/2;
r_guide = 3;

//For 6 gauge (3.5mm) screw
r_screw = 1.8;
z_screw = 20;

//For support post
r_post = r_front/6;
z_post = 20;



////////////////// MODULES //////////////////////////////

// Modelling the ends for the lid
module lid_front(x_out, y_out, r_front)
hull(){
    translate([r_front, r_front, r_front])
    {
        sphere(r = r_front);
    }
    
    translate([x_out - r_front, r_front, r_front])
    {
        sphere(r = r_front);
    }
}

// Cutting the spheres from the lid front
module cut_front(r_cut)
{
    translate([- r_cut, r_cut, r_cut])
    {
        rotate([0, 90, 0])cylinder(r = r_cut, h = x_out+30);
    }
}

// Modelling the slider
module slider(x_slid, y_slid, z_slid, r_slid)
{
    translate([(x_out/2)-(x_slid/2), (0.9)*r_slid, 1.83*r_slid])
    {
        //hull to round out the edges of the slider
        hull(){
            translate([r_slid, r_slid, 0])
            {
                cylinder(r = r_slid, h = z_slid);
            }
            
            translate([x_slid - r_slid, r_slid, 0])
            {
                cylinder(r = r_slid, h = z_slid);
            }
            
            translate([r_slid, y_slid - r_slid, 0])
            {
                cylinder(r = r_slid, h = z_slid);
            }
            
            translate([x_slid - r_slid, y_slid - r_slid, 0])
            {
                cylinder(r = r_slid, h = z_slid);
            }
        }
     }
}
// Modelling the hole for braille cell
module braille_cell(x_cell, y_cell, z_cell)
{
    translate([(x_out/2)-(x_cell/2), (2*r_front+gap), (1.73*r_front)])
    {
        cube([x_cell, y_cell, z_cell]);
    }
}


//For the guide line
module guide_line(x_guide, y_guide, r_guide)
{
    translate([(x_out/2)-(x_slid/2),(2*r_front+gap)+(y_cell/2), (1.83*r_front)])
    {
        lid_front(x_guide, y_guide, r_guide);
    }
    
    translate([x_out-(x_out-x_slid)/2- x_guide,(2*r_front+gap)+(y_cell/2), (1.83*r_front)])
    {
        lid_front(x_guide, y_guide, r_guide);
    }
}

//For the button plate
module bplate(x_bplate, y_bplate, z_bplate)
{
    translate([r_front, (0.2)*r_front, -(0.5)*z_bplate])
    {
        cube([x_bplate, y_bplate, z_bplate]);
    }
}

//For buttons
module button(x_but, y_but, z_but)
{
    translate([r_front+ ((x_bplate/3)-x_but)/2, 0,-z_but*(0.6)])
    {
        cube([x_but, y_but, z_but]);
    }
    
    translate([r_front+ (x_bplate/3)+((x_bplate/3)-x_but)/2, 0, -z_but/2])
    {
        cube([x_but, y_but, z_but]);
    }
    
    translate([r_front+ 2*(x_bplate/3)+((x_bplate/3)-x_but)/2, 0, -z_but/2])
    {
        cube([x_but, y_but, z_but]);
    }
    
}

//For screw holes
module screw_hole(r_screw, z_screw)
{
    translate([r_front, (0.9)*r_front+(y_slid/2), (1.8*r_front)-z_screw+8])cylinder(r = r_screw, h = z_screw);
}

// For support posts
/*module sup_post(r_post, z_post)
{
    translate([r_front/2, r_front+r_post, -r_front/2])cylinder(r = r_post, h = z_post);
} */

//Whole lid
module v3_var4_lid_sv1()
{
    difference()
    {
        lid_front(x_out, y_out, r_front);
        translate([0,r_front*(0.5), 0])cut_front(r_cut);
        translate([0, r_cut-r_front, -r_front/2])cube([x_out+30, 2*r_front, r_front]);
    }
    difference()
    {
        #slider(x_slid, y_slid, z_slid, r_slid);
        //braille_cell(x_cell, y_cell, z_cell);
        //screw_hole(r_screw, z_screw);
        //translate([x_out - 2*r_front, 0, 0])screw_hole(r_screw, z_screw);
        
        
    }
    
    //translate([r_slid, y_out - r_slid,  1.83*r_slid+z_slid])iso_std_braille_alpha("ABCDEFGHIJKLM", 8);
    
    //translate([r_slid, y_out - (1.7)*r_slid,  1.83*r_slid+z_slid])iso_std_braille_alpha("NOPQRSTUVWXYZ", 8);
    //guide_line(x_guide, y_guide, r_guide);
    
    difference()
    {
        bplate(x_bplate, y_bplate, z_bplate);
        //button(x_but, y_but, z_but);
        
    }
    
    
    
    //sup_post(r_post, z_post);
   // translate([x_out - r_front, 0, 0])sup_post(r_post, z_post);
    
    screw_hole(r_screw, z_screw);
    translate([x_out - 2*r_front, 0, 0])screw_hole(r_screw, z_screw);
    
    
}



    




/****************** BUILDING THE MODEL **************/
//v3_var4_lid_sv1();
