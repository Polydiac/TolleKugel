 

/**
 *  Processing-penner-easing
 */
public class Easings {
    /**
     *
     * @param t Current Time
     * @param b Beginning Value
     * @param c Wanted Change of the given Value
     * @param d Duration of the supposed Aninmation
     * @return
     */

    public static float  sineEaseIn(float t,float b , float c, float d) {
        return -c * (float)Math.cos(t/d * (Math.PI/2)) + c + b;
    }

    public static float  sineEaseOut(float t,float b , float c, float d) {
        return c * (float)Math.sin(t/d * (Math.PI/2)) + b;
    }

    public static float  sineEaseInOut(float t,float b , float c, float d) {
        return -c/2 * ((float)Math.cos(Math.PI*t/d) - 1) + b;
    }

    public static float quintEaseIn (float t,float b , float c, float d) {
        return c*(t/=d)*t*t*t*t + b;
    }

    public static float quintEaseOut (float t,float b , float c, float d) {
        return c*((t=t/d-1)*t*t*t*t + 1) + b;
    }

    public static float quintEaseInOut (float t,float b , float c, float d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
        return c/2*((t-=2)*t*t*t*t + 2) + b;
    }

    public static float  quartEaseIn(float t,float b , float c, float d) {
        return c*(t/=d)*t*t*t + b;
    }

    public static float  quartEaseOut(float t,float b , float c, float d) {
        return -c * ((t=t/d-1)*t*t*t - 1) + b;
    }

    public static float  quartEaseInOut(float t,float b , float c, float d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
        return -c/2 * ((t-=2)*t*t*t - 2) + b;
    }

    public static float  quadEaseIn(float t,float b , float c, float d) {
        return c*(t/=d)*t + b;
    }

    public static float  quadEaseOut(float t,float b , float c, float d) {
        return -c *(t/=d)*(t-2) + b;
    }

    public static float  quadEaseInOut(float t,float b , float c, float d) {
        if ((t/=d/2) < 1) return c/2*t*t + b;
        return -c/2 * ((--t)*(t-2) - 1) + b;
    }

    public static float linearEaseNone (float t,float b , float c, float d) {
        return c*t/d + b;
    }

    public static float linearEaseIn (float t,float b , float c, float d) {
        return c*t/d + b;
    }

    public static float linearEaseOut (float t,float b , float c, float d) {
        return c*t/d + b;
    }

    public static float linearEaseInOut (float t,float b , float c, float d) {
        return c*t/d + b;
    }

    public static float  expoEaseIn(float t,float b , float c, float d) {
        return (t==0) ? b : c * (float)Math.pow(2, 10 * (t/d - 1)) + b;
    }

    public static float  expoEaseOut(float t,float b , float c, float d) {
        return (t==d) ? b+c : c * (-(float)Math.pow(2, -10 * t/d) + 1) + b;
    }

    public static float  expoEaseInOut(float t,float b , float c, float d) {
        if (t==0) return b;
        if (t==d) return b+c;
        if ((t/=d/2) < 1) return c/2 * (float)Math.pow(2, 10 * (t - 1)) + b;
        return c/2 * (-(float)Math.pow(2, -10 * --t) + 2) + b;
    }

    public static float  elasticEaseIn(float t,float b , float c, float d ) {
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        float p=d*.3f;
        float a=c;
        float s=p/4;
        return -(a*(float)Math.pow(2,10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )) + b;
    }

    public static float  elasticEaseIn(float t,float b , float c, float d, float a, float p) {
        float s;
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        if (a < Math.abs(c)) { a=c;  s=p/4; }
        else { s = p/(2*(float)Math.PI) * (float)Math.asin (c/a);}
        return -(a*(float)Math.pow(2,10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
    }

    public static float  elasticEaseOut(float t,float b , float c, float d) {
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        float p=d*.3f;
        float a=c;
        float s=p/4;
        return (a*(float)Math.pow(2,-10*t) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p ) + c + b);
    }

    public static float  elasticEaseOut(float t,float b , float c, float d, float a, float p) {
        float s;
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        if (a < Math.abs(c)) { a=c;  s=p/4; }
        else { s = p/(2*(float)Math.PI) * (float)Math.asin (c/a);}
        return (a*(float)Math.pow(2,-10*t) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p ) + c + b);
    }

    public static float  elasticEaseInOut(float t,float b , float c, float d) {
        if (t==0) return b;  if ((t/=d/2)==2) return b+c;
        float p=d*(.3f*1.5f);
        float a=c;
        float s=p/4;
        if (t < 1) return -.5f*(a*(float)Math.pow(2,10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )) + b;
        return a*(float)Math.pow(2,-10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )*.5f + c + b;
    }

    public static float  elasticEaseInOut(float t,float b , float c, float d, float a, float p) {
        float s;
        if (t==0) return b;  if ((t/=d/2)==2) return b+c;
        if (a < Math.abs(c)) { a=c; s=p/4; }
        else { s = p/(2*(float)Math.PI) * (float)Math.asin (c/a);}
        if (t < 1) return -.5f*(a*(float)Math.pow(2,10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )) + b;
        return a*(float)Math.pow(2,-10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )*.5f + c + b;
    }

    public static float cubicEaseIn (float t,float b , float c, float d) {
        return c*(t/=d)*t*t + b;
    }

    public static float cubicEaseOut (float t,float b , float c, float d) {
        return c*((t=t/d-1)*t*t + 1) + b;
    }

    public static float cubicEaseInOut (float t,float b , float c, float d) {
        if ((t/=d/2) < 1) return c/2*t*t*t + b;
        return c/2*((t-=2)*t*t + 2) + b;
    }

    public static float  circEaseIn(float t,float b , float c, float d) {
        return -c * ((float)Math.sqrt(1 - (t/=d)*t) - 1) + b;
    }

    public static float  circEaseOut(float t,float b , float c, float d) {
        return c * (float)Math.sqrt(1 - (t=t/d-1)*t) + b;
    }

    public static float  circEaseInOut(float t,float b , float c, float d) {
        if ((t/=d/2) < 1) return -c/2 * ((float)Math.sqrt(1 - t*t) - 1) + b;
        return c/2 * ((float)Math.sqrt(1 - (t-=2)*t) + 1) + b;
    }

    public static float  bounceEaseIn(float t,float b , float c, float d) {
        return c - bounceEaseOut (d-t, 0, c, d) + b;
    }

    public static float  bounceEaseOut(float t,float b , float c, float d) {
        if ((t/=d) < (1/2.75f)) {
            return c*(7.5625f*t*t) + b;
        } else if (t < (2/2.75f)) {
            return c*(7.5625f*(t-=(1.5f/2.75f))*t + .75f) + b;
        } else if (t < (2.5/2.75)) {
            return c*(7.5625f*(t-=(2.25f/2.75f))*t + .9375f) + b;
        } else {
            return c*(7.5625f*(t-=(2.625f/2.75f))*t + .984375f) + b;
        }
    }

    public static float  bounceEaseInOut(float t,float b , float c, float d) {
        if (t < d/2) return bounceEaseIn (t*2, 0, c, d) * .5f + b;
        else return bounceEaseOut (t*2-d, 0, c, d) * .5f + c*.5f + b;
    }

    public static float  backEaseIn(float t,float b , float c, float d) {
        float s = 1.70158f;
        return c*(t/=d)*t*((s+1)*t - s) + b;
    }

    public static float  backEaseIn(float t,float b , float c, float d, float s) {
        return c*(t/=d)*t*((s+1)*t - s) + b;
    }

    public static float  backEaseOut(float t,float b , float c, float d) {
        float s = 1.70158f;
        return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
    }

    public static float  backEaseOut(float t,float b , float c, float d, float s) {
        return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
    }

    public static float  backEaseInOut(float t,float b , float c, float d) {
        float s = 1.70158f;
        if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525f))+1)*t - s)) + b;
        return c/2*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2) + b;
    }

    public static float  backEaseInOut(float t,float b , float c, float d, float s) {
        if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525f))+1)*t - s)) + b;
        return c/2*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2) + b;
    }
}
