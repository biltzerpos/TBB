/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3
        CREATED BY: SHWETA DIXIT - 2017
        
        TITLE --- VERSION 3 - VARIANT 2 - OUTER ROUNDED BOX
        
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

//// FOR RASPBERRY PI HOLES ////

//For Right side hole
x_side = 50;
y_side = 60;
z_side = 15;

// For hole at the back
x_back = 60;
y_back = 50;
z_back = 25;

// For top slider layer
x_top = x_in + 12;
y_top = y_in + (y_out - y_in)/2 + 5;
z_top = 6; // height of top slider layer

// For removing the top half to fit in the slider
x_half = x_out;
y_half = r_out/2;
z_half = z_out/2;

    
////////////////// MODULES //////////////////////////////

// Modelling outer rounded cube
module outer_cube(x_out, y_out, z_out, r_out)
hull(){
    translate([r_out, r_out, 0])
    {
        cylinder(r = r_out, h = z_out);
    }
    
    translate([x_out - r_out, r_out, 0])
    {
        cylinder(r = r_out, h = z_out);
    }
    
    translate([r_out, y_out - r_out, 0])
    {
        cylinder(r = r_out, h = z_out);
    }
    
    translate([x_out - r_out, y_out - r_out, 0])
    {
        cylinder(r = r_out, h = z_out);
    }
    
}

/*rotate([90, 0, 90]) translate([r_out, z_out - r_out, r_out])
    {
        cylinder(r = r_out, h = x_out- (2*r_out));
    }*/

//Modelling inner cube
module inner_cube(x_in, y_in, z_in)
{
    translate([((x_out - x_in)/2), ((y_out - y_in)/2), z_in - z_out])
    {
        cube([x_in, y_in, z_in]);
    }
}



// Modelling right and back 
module side_hole(x_side, y_side, z_side)
{
    translate([(x_in + (x_out - x_in)/2), ((y_out - y_in)/2), (z_in - z_out)])
    {
        cube([x_side, y_side, z_side]);
    }
}

module back_hole(x_back, y_back, z_back)
{
    translate([(((x_out - x_in)/2) + (x_in - x_back)), (((y_out - y_in)/2)+ y_in), (z_in - z_out)])
    {
        cube([x_back, y_back, z_back]);
    }
}


// Modelling the top slider
module top_slider(x_top, y_top, z_top)
{
    translate([((x_out - x_in)/4)+2, 0, z_out - 10])
    {
        cube([x_top, y_top, z_top]);
    }
}

// Removing the top half to fit the slider
module top_half(x_half, y_half, z_half)
{
    translate([0, 0, z_out - z_half])
    {
        cube([x_half, y_half, z_half]);
        /*rotate([90, 0, 90])
       {
           translate([0, y_half, 0])
           {
               cylinder(r = r_out, h = x_half);
           }
       }*/
    }
}
    

/////////////// BUILDING MODEL //////////////////

// Building the outer box+

difference()
{
    outer_cube(x_out, y_out, z_out, r_out);    
    inner_cube(x_in, y_in, z_in);  
    side_hole(x_side, y_side, z_side);
    back_hole(x_back, y_back, z_back);  
    top_slider(x_top, y_top, z_top);  
    top_half(x_half, y_half, z_half);    
}




