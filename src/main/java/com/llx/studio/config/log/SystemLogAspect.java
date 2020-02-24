package com.llx.studio.config.log;


import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.OperLogDao;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.GetIp;
import com.llx.studio.util.constants.Constants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {

    //相当于dao的对象
    @Autowired
    private OperLogDao operLogDao;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);


    //Service层切点
    @Pointcut("@annotation(com.llx.studio.config.log.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.llx.studio.config.log.SystemControllerLog)")
    public void controllerAspect() {
    }

    /*
    @Before 前置通知，在方法执行之前执行
    @After 后置通知，在方法执行之后执行
    @AfterRuturning 返回通知，在方法返回结果之后执行
    @AfterThrowing 异常通知，在方法抛出异常之后执行
    @Around 环绕通知，围绕着方法执行
     */

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "controllerAspect()")
    public void doAfterReturning(JoinPoint joinPoint)
    {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e);
    }
    protected void handleLog(final JoinPoint joinPoint, final Exception e)
    {
        try
        {
            // 获取当前的用户
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            JSONObject user = (JSONObject)request.getSession().getAttribute(Constants.SESSION_USER_INFO);

            // *========数据库日志=========*//
            JSONObject jsonObject = new JSONObject();
            //状态
            jsonObject.put("operStatus",0);//正常
            jsonObject.put("errorMsg","0");
            // 请求的地址
            String ip = GetIp.getRemoteIpByServletRequest(request,true);
            jsonObject.put("operIp",ip);

            jsonObject.put("operUrl",request.getRequestURI());
            if (user != null)
            {
                jsonObject.put("operName",user.getString("userName"));
            }

            if (e != null)
            {
                jsonObject.put("operStatus",1);//异常
                jsonObject.put("errorMsg",e.getMessage());
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            jsonObject.put("oper_method",className + "." + methodName + "()");
            // 处理设置注解上的参数
            String controllerMethodDescription = getControllerMethodDescription(joinPoint);
            jsonObject.put("operDescription",controllerMethodDescription);
            jsonObject.put("operTime",System.currentTimeMillis()/1000+"");
            JSONObject jsonObjectParam = CommonUtil.request2Json(request);
            if (jsonObjectParam.size() > 0){
                jsonObject.put("operParam", CommonUtil.request2Json(request).toString());
            }else {
                jsonObject.put("operParam","");
            }

            // 保存数据库
            operLogDao.addOperLog(jsonObject);

        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }


    /**
     * 获取用户设备类型
     * @param requestHeader request.getHeader("user-agent");
     * @return true 手机浏览器
     *          false 电脑浏览器
     */
    public static Boolean isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){

                return true;
            }
        }
        return false;

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


}
