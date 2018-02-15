/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 3 - VARIANT 2 - LID FOR THE ROUNDED BOX
        
        *******ATTEMPT TWO***********
        
        (Measurements in mm)
        
        
*/

$fn = 100;

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
x_base = x_out - 2*r_edge + 10;
y_base = r_edge/2;
z_base = z_edge;

// For top slider layer
x_slid = x_in + 10;
y_slid = y_in + (y_out - y_in)/2 + 3;
z_slid = 3; // height of top slider layer

// For the top bump
z_bump = x_base;
r_bump = 10;

// For the braille cell
x_cell = 6;
y_cell = 27.5;
z_cell = 3;

// Gap between bump and braille cell
gap = 5;

// For buttons
x_but = 15.8;
y_but = 3*y_base;
z_but = 15.8;

// For the guide lines
z_line = z_bump/2;
r_line = 1;

// Height for braille cell
h = 0.9+z_slid;

    
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
        translate([-(2*r_edge + x_base)+10, 0, -(r_edge)])
        {
            out_edge_cyl1(z_edge, r_edge);
        }
    }
}

// Modelling the lower base
module lower_base(x_base, y_base, z_base)
{
    translate([r_edge - 10, 0, 0])
    {
        cube([x_base, y_base, z_base]);
    }
}

// Modelling the slider part
module slider(x_slid, y_slid, z_slid, r_edge)
translate([(x_out/2) - (x_slid/2), 0, z_edge - z_slid])
{
        
    hull(){
            translate([r_edge, r_edge, 0])
            {
                cylinder(r = r_edge, h = z_slid);
            }
            
            translate([x_slid - r_edge, r_edge, 0])
            {
                cylinder(r = r_edge, h = z_slid);
            }
            
            translate([r_edge, y_slid - r_edge, 0])
            {
                cylinder(r = r_edge, h = z_slid);
            }
            
            translate([x_slid - r_edge, y_slid - r_edge, 0])
            {
                cylinder(r = r_edge, h = z_slid);
            }
        }
}

// Modelling the bump before the braille cell
module bump(z_bump, r_bump)
{
    translate([(r_edge-10)+(x_base - z_bump)/2, r_edge/2, z_edge])
    {
        rotate([0, 90, 0])
        {
            difference()
            {
                cylinder(r = r_bump, h = z_bump);
                
                translate([r_bump, - r_edge/2, 0])
                {
                    rotate([0, 0, 90])
                    {
                        cube([4*r_bump, r_bump, z_bump]);
                    }
                }
            }
        }
    }
}

// Modelling the hole for braille cell
module braille_cell(x_cell, y_cell, z_cell)
{
    translate([(r_edge-10) + (z_bump/2)-(x_cell/2), (r_edge/2)+ r_bump + gap, z_edge - z_slid])
    {
        cube([x_cell, y_cell, z_cell]);
    }
}

// Modelling hole for button 1
module but_1(x_but, y_but, z_but)
{
    translate([((r_edge-10)+(x_base/2)-(x_but/2))/2 + (x_but/2) , 0, (z_base/2) - (z_but/2)])
    {
        
        cube([x_but, y_but, z_but]);
    }
}

// Modelling hole for button 2
module but_2(x_but, y_but, z_but)
{
    translate([(r_edge-10)+(x_base/2)-(x_but/2) , 0, (z_base/2) - (z_but/2)])
    {
        
        cube([x_but, y_but, z_but]);
    }
}

// Modelling hole for button 3
module but_3(x_but, y_but, z_but)
{
    translate([(r_edge -10  + x_base)- (x_base/2) + (1.5* x_but) , 0, (z_base/2) - (z_but/2)])
    {
        
        cube([x_but, y_but, z_but]);
    }
}

//// Modelling the guide lines
module guide_line1(z_line, r_line)
{
    translate([r_edge - 20, ((r_edge/2)+ r_bump + gap + (y_cell/2)), z_edge - z_slid + 3*r_line])
    {
        rotate([0, 90, 0])
        {
            cylinder(r = r_line, h = z_line);
        }
    }
}

module guide_line2(z_line, r_line)
{
    translate([(r_edge-10)+ (z_line + x_cell)+ 5, ((r_edge/2)+ r_bump + gap + (y_cell/2)), z_edge - z_slid + 3*r_line])
    {
        rotate([0, 90, 0])
        {
            cylinder(r = r_line, h = z_line);
        }
    }
}

// Adding the unicode alphabet
module alphabet(h)
{
    translate([r_edge -10, y_slid - (r_edge/2), z_edge - z_slid])
    {
        linear_extrude(height = h)
        text("\u2801\u2803\u2809\u2819\u2811\u280B\u281B\u2813\u280A\u281A\u2805\u2807\u280D", font = "Segoe UI Symbol", size = 8);
    }
    
    translate([r_edge, y_slid - (r_edge/1.1), z_edge - z_slid])
    {
        linear_extrude(height = h)
        text("\u281D\u2815\u280F\u281F\u2817\u280E\u281E\u2825\u2827\u283A\u282D\u283D\u2835", font = "Segoe UI Symbol", size = 8);
    }
    
    
}

    

/////////////// BUILDING MODEL //////////////////

// Building the lid
out_edge_cyl1(z_edge, r_edge);
out_edge_cyl2(z_edge, r_edge);

difference()
{
    lower_base(x_base, y_base, z_base);
    but_1(x_but, y_but, z_but);
    but_2(x_but, y_but, z_but);
    but_3(x_but, y_but, z_but);
}

difference()
{
    slider(x_slid, y_slid, z_slid, r_edge);
    braille_cell(x_cell, y_cell, z_cell);    
}

bump(z_bump, r_bump);
guide_line1(z_line, r_line);
guide_line2(z_line, r_line);
alphabet(h);