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

    //为了在动画开始之前让所有像素保持不可见，我们需要适当调整亮度值和u_time的比较方式
    //这样在u_time小于任何亮度值之前，所有像素都将不可见,确保亮度低于u_time的像素被显示
    float alpha = step(brightness, u_time);

    gl_FragColor = vec4(texColor.rgb, texColor.a * alpha);

}