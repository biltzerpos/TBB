
$fn = 100;

hole_rad = 1.2; // Actual rad = 1.375
hole_h = 5;

pi_mount_h = 5;
pi_mount_rad = 3;
y_dist_mount = 58;
x_dist_mount = 49;

x_base = 85;
y_base = 56;
z_base = 5;

/*module pi_base(x_base, y_base, z_base)
{
    cube([x_base, y_base, z_base]);
}*/

module pi_mount(pi_mount_rad, pi_mount_h)
{
    translate([pi_mount_rad+0.5, pi_mount_rad+0.5, 0])
    {
        cylinder(r = pi_mount_rad, h = pi_mount_h);
    }
    
    translate([pi_mount_rad+0.5+x_dist_mount, pi_mount_rad+0.5, 0])
    {
        cylinder(r = pi_mount_rad, h = pi_mount_h);
    }
    
    translate([pi_mount_rad+0.5, pi_mount_rad+0.5+y_dist_mount, 0])
    {
        cylinder(r = pi_mount_rad, h = pi_mount_h);
    }
    
    translate([pi_mount_rad+0.5+x_dist_mount, pi_mount_rad+0.5+y_dist_mount, 0])
    {
        cylinder(r = pi_mount_rad, h = pi_mount_h);
    }
}

module pi_mount_hole(hole_rad, hole_h)
{
    translate([0,0,pi_mount_h])
    {
        translate([0.5+pi_mount_rad,0.5+pi_mount_rad, 0])
        {
            cylinder(r = hole_rad, h = hole_h);
        }
        
        translate([0.5+pi_mount_rad+x_dist_mount,0.5+pi_mount_rad, 0])
        {
            cylinder(r = hole_rad, h = hole_h);
        }
        
        translate([0.5+pi_mount_rad,0.5+pi_mount_rad+y_dist_mount, 0])
        {
            cylinder(r = hole_rad, h = hole_h);
        }
        
        translate([0.5+pi_mount_rad+x_dist_mount,0.5+pi_mount_rad+y_dist_mount, 0])
        {
            cylinder(r = hole_rad, h = hole_h);
        }
    }
}

module pi_post(pi_mount_rad, pi_mount_h, hole_rad, hole_h)
{
    pi_mount(pi_mount_rad, pi_mount_h);
    pi_mount_hole(hole_rad, hole_h);
}

//pi_base(x_base, y_base, z_base);
//pi_post(pi_mount_rad, pi_mount_h, hole_rad, hole_h);