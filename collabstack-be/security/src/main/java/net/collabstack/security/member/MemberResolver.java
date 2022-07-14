package net.collabstack.security.member;

public interface MemberResolver<T> {

    SecurityMember<T> resolveMember(final T id);

}
