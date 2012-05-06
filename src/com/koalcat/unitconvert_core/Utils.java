package com.koalcat.unitconvert_core;


public class Utils {
	
	static class Speed{
		static double MPS = 1;
		static double KMPS = 1000*MPS;
		static double KMPH = 5*MPS/18;
		static double INPS = 0.0254*MPS;
		static double ipm = INPS/60;
		static double iph = ipm/60;
		static double FTPS = 0.3048*MPS;
		static double FTPM = FTPS/60;
		static double FTPH = FTPM/60;
		static double MIPH = 0.44704*MPS;
		static double mpm = MIPH*60;
		static double mps = mpm*60;
		static double Kn = 0.5144444444*MPS;
		static double kn = 0.5147733333*MPS;
		static double C = 299792458*MPS;
		static double s = 340*MPS;
	}
	static class Length{
		
		static double M = 1;
		static double KM = 1000*M;
		static double AU = 149597871.464*KM;
		static double DM = 0.1*M;
		static double CM = 0.1*DM;
		static double MM = 0.001*M;
		static double UM = 1E-6*M;
		static double A = 1E-10*M;
		static double NM = 10*A;
		
		
		static double LI = 500*M;
		static double CHI = M/3;
		static double ZHANG = 10*CHI;
		static double CUN = 0.1*CHI;
		static double FEN = 0.01*CHI;
		static double Li = 0.001*CHI;
		static double HAO = 0.0001*CHI;
		
		
		static double NMI = 1852*M;
		static double nmi = 1853.184*M;
		static double MI = 1609.344*M;
		static double FG = 0.125*MI;
		static double FM = 1.8288*M;
		static double YD = 0.5*FM;
		static double IN = 2.54*CM;
		static double FT = 12*IN;
		
		static double ly = 9.4607E15*M;
		static double pc = 3.0857E16*M;
		static double ch = 20.1168*M;
		static double fem = 1E-5*A;
		
		static double lnk = 0.001*FG;
		static double lea = 24000*lnk;
		static double mil = 0.001*IN;
		
		static double ell = 1.143*M;
		static double xu = 1.0021E-3*A;
		static double rd = 5.0292*M;
		static double nl = 5556*M;
		
		static double pace = 0.762*M;
		static double palm = 0.0762*M;
		
		static double link1 = 0.201168*M;
		static double link2 = 0.3048*M;
	}
	
	static class Time{
		static double S = 1;
		static double MIN = 60*S;
		static double H = 60*MIN;
		static double D = 24*H;
		static double WEEK = 7*D;
		static double YEAR = 365*D;
		static double MS = 0.001*S;
		static double US = 0.001*MS;
		static double NS = 0.001*US;
		static double PS = 0.001*NS;
		static double FS = 0.001*PS;
	}
	
	static class Weight{
		
		static double G = 1;
		static double KG = 1000*G;
		static double Q = 100*KG;
		static double TON = 10*Q;
		static double MG = 0.001*G;
		static double UG = 0.001*MG;
		
		static double DAN = 50*KG;
		static double JING = 0.5*KG;
		static double LIANG = 50*G;
		static double QIAN = 0.1*LIANG;
		
		static double LB = 453.59237*G;
		static double OZ = 0.0625*LB;
		static double CT = 0.2*G;
		static double GR = 64.79891*MG;
		static double IT = 1016*KG;
		static double ST = 2000*LB;

		static double YINGSHI = 14*LB;
		static double DR = 0.0625*OZ;
		
		static double drt = 3.8879346*G;
		static double ozt = 8*drt;
		static double lbt = 12*ozt;
		static double sap = drt/3;
		static double gr = sap/20;
		static double slug = 14.593902937*KG;
		static double kip = 0.5*ST;
		static double dwt = 24*gr;
		
		static double gamma = UG;
		static double grave = KG;
		static double ozav = 28.349523125*G;
	}
	
	static class Force{
		static double N = 1;
		static double KP = 9.80665*N;
		static double PDL = 0.13825495438*N;
		static double GF = 0.001*KP;
		static double DYN = 0.00001*N;
		static double KN = 1000*N;
		static double LBF = 4.4482216153*N;
		static double KIP = 1000*LBF;
	}
	
	static class power{
		static double W = 1;
		static double KW= 1000*W;
		static double mw = 1000*KW;
		static double HP = 735.49875*W;
		static double hp = 745.699871582*W;
		static double PS = 735.2941*W;
		static double KGM_S = 9.8039215*W;
		static double KCAL_S = 4184.1004*W;
		static double BTU_S = 1055.07491*W;
		static double BTU_m = BTU_S/60;
		static double BTU_h = BTU_m/60;
		static double FTLB_S = 1.3557484*W;
		static double FTLB_m = FTLB_S/60;
		static double FTLB_h = FTLB_m/60;
		static double J_S = 1*W;
		static double NM_S = 1*W;
		static double NM_M = 60/NM_S;
		static double NM_H = 60/NM_M;
	}

	static class area{
		static double M2 = 1;
		static double CM2 = 0.0001*M2;
		static double MU = 666.6667*M2;
		static double KM2 = 1000000*M2;
		static double HA = 10000*M2;
		static double ACRE = 4046.8564224*M2;
		static double ac = 4046.873*M2;
		static double OM = 0.00064516*M2;
		static double MILE2 = 2589988.110336*M2;
		static double mile2 = 2589998.47*M2;
		static double FT2 = 0.09290304*M2;
		static double ft2 =  0.0929034116132749*M2;
		
		static double YD2 = 0.83612736*M2;
		static double are = 100*M2;
		static double dunam = 1000*M2;
		static double b = 1E-28*M2;
		static double barony = 4000*ac;
		static double bd = 0.00774192*M2;
		static double circin = 0.0005067075*M2;
		
		static double cord = 192*bd;
		static double guntha = 121*YD2;
		static double ro = ACRE/4;
		static double sqrd = 25.29285264*M2;
		static double sqmil = 0.00064516*KM2;
		static double sqchi = 404.68564224*M2;
		static double sqchu = 404.6873*M2;
	}

	static class volume{
		static double M3 = 1;
		static double L = 0.001*M3;
		static double CM3 = 0.000001*M3;
		static double FT3 = 0.0283168*M3;
		static double IN3 = 0.0000163871*M3;
		static double yd3 = 27 *FT3;
		static double UKGAL = 0.00454609*M3;
		static double USGAL = 0.003785411784*M3; 
		static double galusdry = 0.00440488377086*M3;
		static double USBBL = 42*USGAL;
		static double fbuk = 36*UKGAL;
		static double fbus = 31.5*USGAL;
		static double usbl = 115.6*L;
		static double bkt = 4*UKGAL;
		static double bu = 8*UKGAL;
		
		static double cus = 0.23659*L;
		static double tspus = cus/48;
		static double cim = 0.284131*L;
		static double cca = 0.2273045*L;
		static double cme = 0.25*L;
		static double tspca = cca/48;
		static double tspim = cim/48;
		static double tspme = cme/48;
		static double tbspus = cus/16;
		static double tbspim = cim/16;
		static double tbspca = cca/16;
		static double tbspme = cme/16;
		static double flozus = 2*tbspus;
		static double flozim = 1.6*tbspim;
		static double dashim = UKGAL/384;
		static double dashus = flozus/96;
		
		static double dropim = flozim/288;
		static double dropmed = CM3/12;
		static double dropmet = CM3/20;
		static double dropus = flozus/360;
		static double piim = 0.125*tspim;
		static double pius = flozus/48;
		static double ptim = tspim/96;
		static double ptusd = galusdry/8;
		static double ptusf = USGAL/8;
		
		static double qtim = UKGAL/4;
		static double qtusd = galusdry/4;
		static double qtusf = USGAL/4;
		
		static double giim = 5*flozim;
		static double gius = 4*flozus;
		
		static double hhdim = 0.32731848*M3;
		static double hhdus = 0.238480942392*M3;
		
		static double fldrim = flozim/8;
		static double fldrus = flozus/8;
		static double fls = flozim/24;
	}

	static class energy{
		static double J = 1;
		static double kj = 1000*J;
		static double mj = 1000*kj;
		static double BTU = 1055.06*J;
		static double ERG = 0.0000001*J;
		static double THERM = 105505585.262*J;
		static double THERM1 = 105480400*J;
		static double KWH = 3600000*J;
		static double FTLB = 1.3557483731*J;
		static double inlb = FTLB/12;
		
		static double cal = 4.1868*J;
		static double kcal = 1000*cal;
		static double ev = 1.60217646E-19*J;
		static double ha = 27.21138386*ev;
		static double quad = 1E15*BTU;
		static double hph = 0.7457*KWH;
		
		static double tce = 29.3076E9*J;
		static double toe = 41.868E9*J;
		static double ttnt = 4.184E9*J;
		static double bboe = 6.12E9*J;
		static double ftpdl = 0.0421401100938048*J;
		
	}
	
	static class pressure{
		static double PA = 1;
		static double KPA = 1000*PA;
		static double MPA = 1000*KPA;
		static double B = 100*KPA;
		static double MB = 100*PA;
		static double ATM = 101325*PA;
		static double MMHG = 133.3224*PA;
		static double cmhg = 10*MMHG;
		static double INHG = 3386.3882*PA;
		
		static double PSI = 6894.7573*PA;
		static double PSF = 144*PA;
		static double kgcm = 98066.5*PA;
		static double kgm = 0.0001*kgcm;
		
		static double mmh2o = kgm;
		static double cmh2o = 10*mmh2o;
		static double inh2o = 249.082*PA;
		
		static double torr = MMHG;
	}
	
	static class money{
		static double USD = 1;
		static double CNY = 0.157*USD;
		static double EUR = 1.346*USD;
		static double HKD = 0.128*USD;
		static double GBP = 1.562*USD;
		static double JPY = 0.013*USD;
		static double TWD = 0.033*USD;
		static double ALL = 0.010*USD;
		static double BBD = 0.500*USD;
		static double BDT = 0.013*USD;
		static double BGN = 0.690*USD;
		static double BHD = 2.652*USD;
		static double BMD = 1*USD;
	}

}