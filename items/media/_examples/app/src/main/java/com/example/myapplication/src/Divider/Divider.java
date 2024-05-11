package com.example.myapplication.src.Divider;
// Template Method Pattern
public abstract class Divider<T> {
    // 模板方法，定义算法实现的共同点，final不允许子类重写，静态方法不依赖于具体实例方便调用
    public static final String[] divide(String input, Divider<String> divider) {
        if (input == null || input.isEmpty()) {
            return new String[0];
        }

        String dividerStr = divider.getDivider();
        String[] result = input.split(dividerStr);

        divider.processResult(result);

        return result;
    }

    // 抽象方法，由子类实现，提供具体的分隔符，必须重写
    protected abstract String getDivider();

    // 钩子方法，对结果进行后处理，子类可以选择重写
    protected void processResult(String[] result) {
        // 默认实现为空，子类可以根据需要进行重写
    }
}