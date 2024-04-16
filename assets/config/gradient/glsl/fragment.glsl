#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;
uniform sampler2D u_texture;
//控制动画的时间参数
uniform float u_time;

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoords);
    //计算像素的亮度
    float brightness = dot(texColor.rgb, vec3(0.299, 0.587, 0.114));
    //根据亮度和时间参数决定是否显示该像素
    float alpha = step(u_time, brightness);
    gl_FragColor = vec4(texColor.rgb, texColor.a * alpha);
}