/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 3 - VARIANT 1 - LID FOR THE ROUNDED BOX
        
        *******ATTEMPT TWO***********
        
        (Measurements in mm)
        
        
*/

////////////////// VARIABLES ////////////////////////////

// For outer rounded cube
x_out = 160;
y_out = 120;
z_out = 80;
r_out = 30;

// For inner cube
x_in = x_out - 30;
y_in = y_out - 30;
z_in = z_out + 5;

// Leaving space at the top for the slider
z_space = 10;

// For the outer rounded edge
r_edge = r_out;
z_edge = (z_out/2) - z_space;

// For cube to cut the cylinders
x_cut = 2*r_edge;
y_cut = r_edge;
z_cut = z_edge;

// For modelling the base
x_base = x_out - (3*r_edge);
y_base = r_edge/2;
z_base = z_edge;

// For top slider layer
x_slid = x_in + 6;
y_slid = y_in + (y_out - y_in)/2 + 3;
z_slid = 6; // height of top slider layer

    
////////////////// MODULES //////////////////////////////

// Modelling cube to cut the cylinders
module cut_cyl(x_cut, y_cut, z_cut)
{
    translate([0, r_edge, 0])
    {
        cube([x_cut, y_cut, z_cut]);
    }
}

// Modelling rotated cube to cut cylinders
module rot_cut_cyl(x_cut, y_cut, z_cut)
{
    rotate([0, 0, 90])
        {
            translate([0, - 2*r_edge, 0])
            {
                cube([x_cut, y_cut, z_cut]);
            }
        }
}


// Modelling outer rounded edges
module out_edge_cyl1(z_edge, r_edge)
{
    difference()
    {
        translate([r_edge, r_edge, 0])
        {
            cylinder(r = r_edge, h = z_edge);
        }
        
        cut_cyl(x_cut, y_cut, z_cut);
        rot_cut_cyl(x_cut, y_cut, z_cut);
        
    }
}

module out_edge_cyl2(z_edge, r_edge)
{
    rotate([0, - 180, 0])
    {
        translate([-(x_out - r_edge), 0, -(r_edge)])
        {
            out_edge_cyl1(z_edge, r_edge);
        }
    }
}

// Modelling the lower base
module lower_base(x_base, y_base, z_base)
{
    translate([r_edge, 0, 0])
    {
        cube([x_base, y_base, z_base]);
    }
}

// Modelling the slider part



    

/////////////// BUILDING MODEL //////////////////

// Building the lid
out_edge_cyl1(z_edge, r_edge);
out_edge_cyl2(z_edge, r_edge);
lower_base(x_base, y_base, z_base);