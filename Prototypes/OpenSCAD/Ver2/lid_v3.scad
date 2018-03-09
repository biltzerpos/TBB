/*
        ENAMEL PROJECT - MELANIE BALJKO
        MODELLING FOR VERSION 3
        SHWETA DIXIT - 2017
        
        TITLE --- SLIDER TOP LID
        
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

/// For slider lid
x_lid = x_in + 3;
y_lid = y_in + 3;
z_lid = 3;
r_lid = r_in;
    
    
/////////////// BUILDING MODEL //////////////////

// Making the slider lid
module slider_lid(x_lid, y_lid, z_lid, r_lid)
hull(){
    translate([r_lid, r_lid, 0])
    {
        intersection()
        {
            cylinder(r = r_lid, h = z_lid);
            cube([r_lid*2, (r_lid), (z_lid)]);
            
        }
    }
    
    translate([x_lid- r_lid, r_lid, 0])
    {
        cylinder(r = r_lid, h = z_lid);
    }
    
    translate([r_lid, y_lid - r_lid, 0])
    {
        cylinder(r = r_lid, h = z_lid);
    }
    
    translate([x_lid - r_lid, y_lid - r_lid, 0])
    {
        cylinder(r = r_lid, h = z_lid);
    }
}
slider_lid(x_lid, y_lid, z_lid, r_lid);

// Adding the bottom closing area
